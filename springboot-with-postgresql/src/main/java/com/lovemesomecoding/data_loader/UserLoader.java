package com.lovemesomecoding.data_loader;

import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import com.lovemesomecoding.entity.address.Address;
import com.lovemesomecoding.entity.member.Member;
import com.lovemesomecoding.entity.member.MemberRepository;

/**
 * It implements Spring Boot’s CommandLineRunner so that it gets run after all the beans are created and registered.
 * 
 * @author folaukaveinga
 *
 */
@Profile({"local"})
public class UserLoader implements CommandLineRunner {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub

        Member user = new Member();
        user.setId(1L);
        user.setUuid("user-33cdbbdd-75ed-44e3-8007-db8b7b8c3808");
        user.setFirstName("Folau");
        user.setLastName("Kaveinga");
        user.setDateOfBirth(LocalDate.of(1986, 8, 15));
        user.setEmail("folaudev@gmail.com");
        user.setPhoneNumber("3109934731");

        Address address = new Address();
        address.setCity("Lehi");
        address.setId(1L);
        address.setState("UT");
        address.setStreet("123 test rd");
        address.setZipcode("84043");

        user.setAddress(address);

        user = memberRepository.saveAndFlush(user);

        System.out.println("USER: " + user.toString());

        user = new Member();
        user.setId(2L);
        user.setUuid("user-22cdbbdd-75ed-44e3-8007-db8b7b8c3808");
        user.setFirstName("Lisa");
        user.setLastName("Kaveinga");
        user.setDateOfBirth(LocalDate.of(1987, 3, 15));
        user.setEmail("folaudev+1@gmail.com");
        user.setPhoneNumber("3109934731");

        address = new Address();
        address.setCity("Lehi");
        address.setId(2L);
        address.setState("UT");
        address.setStreet("123 test rd");
        address.setZipcode("84043");

        user.setAddress(address);

        user = memberRepository.saveAndFlush(user);

        System.out.println("USER: " + user.toString());

        user = new Member();
        user.setId(3L);
        user.setUuid("user-22cdbbdd-75ed-44e3-8007-db8b7b8c3832");
        user.setFirstName("Laulau");
        user.setLastName("Kaveinga");
        user.setDateOfBirth(LocalDate.of(2011, 7, 15));
        user.setEmail("folaudev+2@gmail.com");
        user.setPhoneNumber("3109934731");

        address = new Address();
        address.setCity("Lehi");
        address.setId(3L);
        address.setState("UT");
        address.setStreet("123 test rd");
        address.setZipcode("84043");

        user.setAddress(address);

        user = memberRepository.saveAndFlush(user);

        System.out.println("USER: " + user.toString());

        user = new Member();
        user.setId(4L);
        user.setUuid("user-22cdbbdd-75ed-44e3-8007-db8b7b8c3831");
        user.setFirstName("Kinga");
        user.setLastName("Kaveinga");
        user.setDateOfBirth(LocalDate.of(2014, 7, 15));
        user.setEmail("folaudev+3@gmail.com");
        user.setPhoneNumber("3109934731");

        address = new Address();
        address.setCity("Lehi");
        address.setId(4L);
        address.setState("UT");
        address.setStreet("123 test rd");
        address.setZipcode("84043");

        user.setAddress(address);

        user = memberRepository.saveAndFlush(user);

        System.out.println("USER: " + user.toString());

        user = new Member();
        user.setId(5L);
        user.setUuid("user-22cdbbdd-75ed-44e3-8007-db8b7b8c3839");
        user.setFirstName("Fusi");
        user.setLastName("Kaveinga");
        user.setDateOfBirth(LocalDate.of(2015, 7, 15));
        user.setEmail("folaudev+4@gmail.com");
        user.setPhoneNumber("3109934731");

        address = new Address();
        address.setCity("Lehi");
        address.setId(5L);
        address.setState("UT");
        address.setStreet("123 test rd");
        address.setZipcode("84043");

        user.setAddress(address);

        user = memberRepository.saveAndFlush(user);

        System.out.println("USER: " + user.toString());

        user = new Member();
        user.setId(6L);
        user.setUuid("user-22cdbbdd-75ed-44e3-8007-db8b7b8c3856");
        user.setFirstName("Mele");
        user.setLastName("Kaveinga");
        user.setDateOfBirth(LocalDate.of(2020, 7, 15));
        user.setEmail("folaudev+5@gmail.com");
        user.setPhoneNumber("3109934731");

        address = new Address();
        address.setCity("Lehi");
        address.setId(6L);
        address.setState("UT");
        address.setStreet("123 test rd");
        address.setZipcode("84043");

        user.setAddress(address);

        user = memberRepository.saveAndFlush(user);

        System.out.println("USER: " + user.toString());

    }

}
