package com.premiumminds.internship.teknonymy;

import com.premiumminds.internship.teknonymy.TeknonymyService;
import com.premiumminds.internship.teknonymy.Person;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;


@RunWith(JUnit4.class)
public class TeknonymyServiceTest {

  /**
   * The corresponding implementations to test.
   *
   * If you want, you can make others :)
   *
   */
  public TeknonymyServiceTest() {
  };

  @Test
  public void PersonNoChildrenTest() {
    Person person = new Person("John",'M',null, LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "";
    assertEquals(result, expected);
  }

  @Test
  public void PersonOneChildTest() {
    Person person = new Person(
            "John",
            'M',
            new Person[]{ new Person("Holy",'F', null, LocalDateTime.of(1046, 1, 1, 0, 0)) },
            LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "father of Holy";
    assertEquals(result, expected);
  }

  @Test
  public void PersonTwoChildTest() {
    Person person = new Person(
            "Johana",
            'F',
            new Person[]{
                    new Person("Holy",
                            'F',
                            null,
                            LocalDateTime.of(1046, 1, 1, 0, 0)),
                    new Person("Mary",
                            'F',
                            null,
                            LocalDateTime.of(1046, 2, 1, 0, 0))
            },
            LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "mother of Holy";
    assertEquals(result, expected);
  }

  @Test
  public void PersonTwoChildOneGrandChildTest() {
    Person person = new Person(
            "John",
            'M',
            new Person[]{
                    new Person("Holy",
                            'F',
                            null,
                            LocalDateTime.of(1046, 1, 1, 0, 0)),
                    new Person("Mary",
                            'F',
                            new Person[]{
                                    new Person("Henry",'M', null, LocalDateTime.of(1064, 3, 2, 0, 0))},
                            LocalDateTime.of(1046, 2, 1, 0, 0))
            },
            LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandfather of Henry";
    assertEquals(result, expected);
  }

  @Test
  public void PersonThreeChildFourGrandChildTest() {
    Person person = new Person(
            "John",
            'M',
            new Person[]{
                    new Person("Holy",
                            'F',
                            null,
                            LocalDateTime.of(1046, 1, 1, 0, 0)),
                    new Person("Mary",
                            'F',
                            new Person[]{
                                    new Person("Henry",'M', null, LocalDateTime.of(1064, 3, 2, 0, 0))},
                            LocalDateTime.of(1046, 2, 1, 0, 0)),
                    new Person("Mathew",
                            'M',
                            new Person[]{
                                    new Person("Carl",'M', null, LocalDateTime.of(1056, 3, 2, 0, 0)),
                                    new Person("Petunia",'F', null, LocalDateTime.of(1054, 3, 2, 0, 0))},
                            LocalDateTime.of(1048, 1, 1, 0, 0))
            },
            LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "grandfather of Petunia";
    assertEquals(result, expected);
  }

  @Test
  public void PersonThreeChildFourGrandChildOneGreatGrandChildTest() {
    Person person = new Person(
            "John",
            'M',
            new Person[]{
                    new Person("Holy",
                            'F',
                            null,
                            LocalDateTime.of(1046, 1, 1, 0, 0)),
                    new Person("Mary",
                            'F',
                            new Person[]{
                                    new Person("Henry",'M', null, LocalDateTime.of(1064, 3, 2, 0, 0))},
                            LocalDateTime.of(1046, 2, 1, 0, 0)),
                    new Person("Mathew",
                            'M',
                            new Person[]{
                                    new Person("Carl",'M', null, LocalDateTime.of(1074, 3, 2, 0, 0)),
                                    new Person("Petunia",'F',  new Person[]{
                                            new Person("Rodrick",'M', null, LocalDateTime.of(1094, 3, 2, 0, 0))}, LocalDateTime.of(1054, 3, 2, 0, 0))},
                            LocalDateTime.of(1036, 1, 1, 0, 0))
            },
            LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-grandfather of Rodrick";
    assertEquals(result, expected);
  }

  @Test
  public void PersonOneGreatGreatGreatGrandChildTest() {
    Person person = new Person(
            "John",
            'M',
            new Person[]{
                    new Person("Holy",
                            'F',
                            null,
                            LocalDateTime.of(1046, 1, 1, 0, 0)),
                    new Person("Mathew",
                            'M',
                            new Person[]{
                                    new Person("Carl",'M', null, LocalDateTime.of(1074, 3, 2, 0, 0)),
                                    new Person("Petunia",'F',  new Person[]{
                                            new Person("Rodrick",'M',  new Person[]{
                                                    new Person("Ricky",'M',  new Person[]{
                                                            new Person("Kya",'F', null, LocalDateTime.of(1125, 3, 2, 0, 0))}, LocalDateTime.of(1100, 3, 2, 0, 0))}, LocalDateTime.of(1094, 3, 2, 0, 0))}, LocalDateTime.of(1054, 3, 2, 0, 0))},
                            LocalDateTime.of(1036, 1, 1, 0, 0))
            },
            LocalDateTime.of(1046, 1, 1, 0, 0));
    String result = new TeknonymyService().getTeknonymy(person);
    String expected = "great-great-great-grandfather of Kya";
    assertEquals(result, expected);
  }
}