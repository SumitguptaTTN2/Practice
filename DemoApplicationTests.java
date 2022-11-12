package com.example.demo;

import com.example.demo.OneToOne.License;
import com.example.demo.OneToOne.Personed;
import com.example.demo.OneToOne.PersonedRepo;
import com.example.demo.OneTomanyMapping.PersonO;
import com.example.demo.OneTomanyMapping.PersonORepo;
import com.example.demo.OneTomanyMapping.PhoneNumber;
import com.example.demo.componentMapping.Adress;
import com.example.demo.componentMapping.Person;
import com.example.demo.componentMapping.PersonRepo;
import com.example.demo.entities.Library;
import com.example.demo.entities.Student;
import com.example.demo.inheitanceMapping.Card;
import com.example.demo.inheitanceMapping.Check;
import com.example.demo.inheitanceMapping.PaymentRepo;
import com.example.demo.jpaPart1.EmpEntity;
import com.example.demo.jpaPart1.EmpRepo;
import com.example.demo.manyTomany.Programmer;
import com.example.demo.manyTomany.ProgrammerRepo;
import com.example.demo.manyTomany.Projects;
import com.example.demo.repos.LibraryRepo;
import com.example.demo.repos.StudentRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class DemoApplicationTests {
    @Autowired
    private LibraryRepo libraryRepository;
    @Autowired
    private StudentRepo studentRepo;
    @Autowired
    private EmpRepo empRepo;
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private PersonRepo personRepo;
    @Autowired
    private PersonORepo personORepo;
    @Autowired
    private ProgrammerRepo programmerRepo;
    @Autowired
    private PersonedRepo personedRepo;


    @Test
    void contextLoads() {
    }

    @Test
    public void testStudent() {
        Student student = new Student();
        student.setStudentName("Sumit");
        Library library = new Library();
        library.setBookName("Aop");
        library.setStudent(student);
        student.setLibrary(library);
        libraryRepository.save(library);
        //    studentRepo.save(student);

    }

    @Test
    public void craeteTest() {
        EmpEntity emp = new EmpEntity();
        emp.setName("deepak");
        emp.setSalary(100);
        empRepo.save(emp);
    }

    @Test
    public void findByNameTest() {
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.ASC, "name");
        List<EmpEntity> empEntities = empRepo.findByName("sumit", pageable);
        empEntities.stream().forEach(p -> System.out.println(p.getName()));
    }

    @Test
    public void testPageable() {
        Pageable pageable = PageRequest.of(0, 2, Sort.Direction.ASC, "name");
        Page<EmpEntity> empEntityPage = empRepo.findAll(pageable);
        empEntityPage.stream().forEach(p -> System.out.println(p.getName()));

    }

    @Test
    public void TestJpql() {
        List<EmpEntity> allEmp = empRepo.findAllEmp();
        System.out.println(allEmp.toString());
    }

    @Test
    public void TestJpqlNamed() {
        List<EmpEntity> allEmp = empRepo.findAllEmpNamed("sumit");
        System.out.println(allEmp.toString());
    }

    @Test
    public void TestJpqlsalaryNamed() {
        List<Object[]> obj = empRepo.findSalaryEmpNamed("sumit");
        for (Object[] object : obj) {
            for (Object arr : object) {
                System.out.println(arr);
            }
        }
    }

    @Test
    @Transactional
    @Rollback(value = false)
    public void dleteEmp() {
        empRepo.deleteEmp("Ram");
    }

    @Test
    public void InheritanceTest() {
        Card cc = new Card();
        cc.setId(1);
        cc.setName("sumit");
        cc.setAmount(10000d);
        cc.setCardNumber("1234567");
        paymentRepo.save(cc);
        Check ch = new Check();
        ch.setId(2);
        ch.setName("RAM");
        ch.setAmount(10000d);
        ch.setCheckNumberr("1234567");
        paymentRepo.save(ch);
    }

    @Test
    void testComponentMapping() {
        Person person = new Person();
        person.setId(1);
        person.setName("sumit");
        Adress adress = new Adress();
        adress.setCity("orsi");
        adress.setPincode("285001");
        person.setAdress(adress);
        personRepo.save(person);

    }

    @Test
    public void testoneToMany() {
        PersonO o = new PersonO();
        o.setName("sumit");
        HashSet<PhoneNumber> phone = new HashSet<>();
        PhoneNumber ph1 = new PhoneNumber();
        ph1.setPhType("cell");
        ph1.setPhNumber("323456789");
        ph1.setPersonO(o);
        phone.add(ph1);
        PhoneNumber ph2 = new PhoneNumber();
        ph2.setPhType("mobile");
        ph2.setPhNumber("4356789");
        ph2.setPersonO(o);
        phone.add(ph2);
        o.setPhoneNumberSet(phone);
        personORepo.save(o);
    }

    @Test
    public void testManyToMany() {
        Programmer programmer = new Programmer();
        programmer.setName("sumit");
        Programmer programmer2 = new Programmer();
        programmer2.setName("Ram");
        Projects projects1 = new Projects();
        projects1.setPName("c++");
        Projects projects2 = new Projects();
        projects2.setPName("java");
        HashSet<Projects> projects = new HashSet<>();
        projects.add(projects1);
        projects.add(projects2);
        programmer.setProjects(projects);
        programmer2.setProjects(projects);
        programmerRepo.save(programmer);

    }

    @Test
    @Transactional
    public void loadMantTomanyData() {
        Programmer programmer = programmerRepo.findById(59).get();
        System.out.println(programmer);
        System.out.println(programmer.toString());

    }

    @Test
    public void TestOneToOne() {
        Personed personed = new Personed();
        personed.setName("sumit Gupta");
        License license = new License();
        license.setLicenseNo("12345678 loice");
        personed.setLicense(license);
        license.setPersoned(personed);
        personedRepo.save(license);

    }
}

