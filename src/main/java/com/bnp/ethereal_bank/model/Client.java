package com.bnp.ethereal_bank.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.AccessLevel;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.bnp.ethereal_bank.model.Exceptions.DepositoFalhadoException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Builder(access = AccessLevel.PUBLIC, builderClassName = "ClientBuilder")
//@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clients")
public class Client implements UserDetails { 

	@Column(name = "id")
	@Id
	private UUID id;

	@Column(name = "nif")
	private String nif;

	@Column(name = "password")
	private String password;

	@Column(name = "name")
	private String name;

	@Column(name = "date_ob")
	private String date_ob;

	@Column(name = "phone_fixed")
	private String fixedPhone;

	@Column(name = "phone_mob")
	private String mobilePhone;

	@Column(name = "email")
	private String email; // relatorio mensal para o email?

	@Column(name = "profession")
	private String profession;

	@Enumerated(EnumType.STRING) // will take the String value of the enum
	private Role role;

	// @Column(name="Accounts")
	@Transient // este campo nao deve ir na db
	private HashMap<Account, LinkedList<Card>> accounts;

	public static Builder builder() {
		return new Builder();
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getnif() {
		return nif;
	}

	public void setnif(String nif) {
		this.nif = nif;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public String getNome() {
		return name;
	}

	public void setNome(String nome) {
		this.name = name;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getdate_ob() {
		return date_ob;
	}

	public void setdate_ob(String date_ob) {
		this.date_ob = date_ob;
	}

	public String getfixedPhone() {
		return fixedPhone;
	}

	public void setfixedPhone(String fixedPhone) {
		this.fixedPhone = fixedPhone;
	}

	public String getmobilePhone() {
		return mobilePhone;
	}

	public void setmobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getprofession() {
		return profession;
	}

	public void setprofession(String profession) {
		this.profession = profession;
	}

	public HashMap<Account, LinkedList<Card>> getAccounts() {
		return accounts;
	}

	public void setAccounts(HashMap<Account, LinkedList<Card>> Accounts) {
		this.accounts = Accounts;
	}

	public void setAutorizado() {
		// this.autorizado= new Autorizado();
	}

	// public Autorizacao getAutorizado() {
	// //return autorizado;
	// // }

	boolean autenticar(int id, int password) {
		// autorizado = null;
		return false;
	}

	public int idade() throws Exception {
		String data_n = getdate_ob();
		if (data_n == null) {

			throw new Exception(
					"Erro: Não tem a sua data de nascimento definida pelo que não é possível criar a conta. ");

		}
		LocalDate dob = LocalDate.parse(data_n);
		LocalDate currentDate = LocalDate.now();
		int idade = Period.between(dob, currentDate).getYears();
		return idade;
	}

	public static Account create_Account(Client client) {

		Account novaAccount = new Account();

		client.accounts.put(novaAccount, novaAccount.cards);
		System.out.println(novaAccount.account_num);
		novaAccount.holders.addFirst(client); // tit_princ fica na primeira posicao - criar hashset?
		return novaAccount;

	}

	void adicionar_Card(AtomicInteger num_Account, Card Card) {
		/*
		 * AtomicInteger objects are mutable, and you are using the == operator to
		 * compare two AtomicInteger objects.
		 * 
		 * The == operator compares the memory references of two objects, which means
		 * that it will only return true if the two objects are the same instance of the
		 * AtomicInteger class.
		 */
		accounts.entrySet().stream()
				.filter(Account_cartoes -> num_Account.intValue() == Account_cartoes.getKey().getNum_Account()
						.intValue()) // Account_Card
				

				.findFirst() // aqui vamos buscar a value correspondente (LinkedList criada no met  a entrada no map que associa a account a um opt
				// findFirst em vez de findAny para que seja Optional onde está definido o
				.orElseThrow(() -> new RuntimeException("Account não encontrada"))
				.getKey() // vai buscar o key objeto do Optional<Entry...

				// .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
				// .collect(collectingAndThen(toList(), ))
				.getcards().add(Card);

	}

	void depositar(String id, double valor)
			throws Exceptions.CardNaoEncontradoException, RuntimeException, Exceptions.DepositoFalhadoException {
		// filter nao declara deposito.falhado exception, portanto precisamos de fazer
		// com Runtime Exception
		accounts.entrySet().stream()
				.filter(Account_cartoes -> {
					LinkedList<Card> cartoes = Account_cartoes.getValue();
					for (Card Card : cartoes) { // filtra -> vê se ha algum que corresponda às condicoes -> vai buscar o
												// 1o -> senao da return false

						if (Card.getId().equals(id)) {
							try {
								Card.depositar(valor);
								return true;
							} catch (DepositoFalhadoException e) {
								throw new RuntimeException(
										"O cartão foi encontrado, mas algo impediu a realização deste depósito");

							}

							/*
							 * ( c1 = Card.getId().equals(id) ? ( true) : ( false) // ternary operator para
							 * avaliar condição
							 * }
							 */
						}
					}
					return false;// default value se nao for encontrado um Card com esse ID
				})
				.findFirst().orElseThrow(() -> new Exceptions.CardNaoEncontradoException(
						"O cartão com o id {id} não foi encontrado nas suas Accounts"));
	}
	// Precisamos de fazer a lambda expression acima porque o orElseThrow precisa de
	// um Supplier e fazendo Exceptions::new (chamando o construtor) nao dá (se
	// calhar por ser inner class?)

	
	@Override
	public String toString() {
		return "Cliente: " + this.name + ", possui o nif " + this.nif + ", tem a password:" + this.password + ", tlm:"
				+ this.mobilePhone;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Client other = (Client) obj;
		return Objects.equals(this.nif, other.nif);
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 61 * hash + Objects.hashCode(this.nif); // Objects e não Object visto que a 1ª permite ter em Account o
														// parâmetro nif (único)
		return hash;
	}

	public Optional<String> getAutorizado() {
		return null;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

	// should return a list of roles, so we need to add a role to the client
	

	return List.of(new SimpleGrantedAuthority(role.name()));
	}

	@Override
	public String getPassword() {

	return password;
	}

	@Override
	public String getUsername() {
	return name;
	}

	@Override
	public boolean isAccountNonExpired() {

	return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	return true;
	}

	@Override
	public boolean isEnabled() {
	return true;
	}

	private Client() {
	}

	public Client(UUID randomUUID, String string, String password2, String name2) {
	}

	// Builder class
	public static class Builder {
		private final Client client = new Client();

		public Builder setId(UUID id) {
			client.id = id;
			return this;
		}

		public Builder setNif(String nif) {
			client.nif = nif;
			return this;
		}

		public Builder setPassword(String password) {
			client.password = password;
			return this;
		}

		public Builder setName(String name) {
			client.name = name;
			return this;
		}

		public Builder setDataNa(String date_ob) {
			client.date_ob = date_ob;
			return this;
		}

		public Builder setfixedPhone(String fixedPhone) {
			client.fixedPhone = fixedPhone;
			return this;
		}

		public Builder setmobilePhone(String mobilePhone) {
			client.mobilePhone = mobilePhone;
			return this;
		}

		public Builder setEmail(String email) {
			client.email = email;
			return this;
		}

		public Builder setprofession(String profession) {
			client.profession = profession;
			return this;
		}

		public Builder setRole(Role role) {
			client.role = role;
			return this;
		}

		public Builder setAccounts(HashMap<Account, LinkedList<Card>> accounts) {
			client.accounts = accounts;
			return this;
		}

		public Client build() {
			return client;
		}

	}

}

// implementing Builder DP para permitir que haja a criação de clientes sem
// alguns parâmetros
// setters necessários para modificar algum parâmetro errado, posteriormente
