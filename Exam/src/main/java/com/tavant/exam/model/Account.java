package com.tavant.exam.model;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account")
public class Account {

	//Below are the columns of the table(Attributes of an account) 

			@Id
			private Integer accountNumber;
			@NotBlank(message="Holder Name should not be blank")
			private String holderName;
			@NotBlank(message="Holder Address should not be blank")
			private String holderAddress;
			@NotNull(message="Age should not be blank")
			private Integer age;
			@NotBlank(message="email should not be blank")
			private String email;


}
