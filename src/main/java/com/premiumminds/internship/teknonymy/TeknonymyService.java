package com.premiumminds.internship.teknonymy;

class TeknonymyService implements ITeknonymyService {
  Integer level = 0; //deepest level, where farthest descendants are
  Integer curr_level=0; //current level that is being explored

  /**
   * Method to get the eldest descendant of a Person, if it exists
   *
   * @param person whose descendants we want to find out
   * @param eldest_desc is the eldest descendant found at the time
   * @return Person which corresponds to the eldest descendant or null if it does not exist
   */
  public Person getEldestDescendant(Person person, Person eldest_desc){
    //reached a leaf in the tree
    if( person.children()==null) {
      //if there are not any descendants
      if (curr_level==0){return null;}

      //this means this is the first descendant found,so save
      if(eldest_desc==null){
        eldest_desc=person;
        level=curr_level;
      }

      //if a deeper descendant was found
      else if(curr_level>level){
        level=curr_level; //update deepest level
        //being the deepest level, this is one of the possible descendants
        //and it should substitute their parents
        eldest_desc = person;
      }

      //comparing descendants at the same depth
      else if(curr_level.equals(level)){
        //check who is the oldest
        if(eldest_desc.dateOfBirth().isAfter(person.dateOfBirth())){
          //method isAfter is provided by the LocalDateTime class
          //returns true only when the first date is after the second date
          eldest_desc = person;
        }
      }

      return eldest_desc ;
    }
    else {
      for(Person sibling: person.children()){
        curr_level = curr_level+1;
        eldest_desc = getEldestDescendant(sibling, eldest_desc);
        curr_level = curr_level-1;

      }
    }
    return eldest_desc;
  }

  /**
   * Method that parses the Teknonymy string
   *
   * @param person whose Teknonymy we are looking for
   * @return String which is the parsed Teknonymy Name
   */
  String parseString(Person person){
    String teknonymy = "";
    if (level == 0) {
      return teknonymy;
    }

    for(int i=1; i<=level; i=i+1){
      if(i == 1){
        if(person.sex().equals('M')){teknonymy=teknonymy+"father of ";}
        else{teknonymy=teknonymy+("mother of ");}
      }
      else if (i == 2) {
        teknonymy = ("grand")+teknonymy;
      } else{
        teknonymy = ("great-")+teknonymy;
      }
    }

    return teknonymy;
  }

  /**
   * Method to get a Person Teknonymy Name
   *
   * @param person whose Teknonymy we are looking for
   * @return String which is the Teknonymy Name
   */
  public String getTeknonymy(Person person) {
    String teknonymy;
    Person eldest = this.getEldestDescendant(person, null);
    if (eldest==null){
      teknonymy="";
    }else {
      teknonymy = parseString(person) + eldest.name();
    }

    return teknonymy;
  }
}
