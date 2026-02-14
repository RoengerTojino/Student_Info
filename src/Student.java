public class Student {
    private final String FIRSTNAME;
    private final String MIDDLENAME;
    private final String LASTNAME;
    private final int AGE;
    private final int YEARLEVEL;
    private final String COURSE;
    private final int SECTION;
    private final int STUDENTNUMBER;
    private final int CONTACTNUMBER;
    private final String BIRTHDAY;

    public String getFirstName(){return FIRSTNAME;}
    public String getMiddleName(){return MIDDLENAME;}
    public String getLastName(){return LASTNAME;}
    public int getAge(){return AGE;}
    public int getYearLevel(){return YEARLEVEL;}
    public String getCourse(){return COURSE;}
    public int getSection(){return SECTION;}
    public int getStudentNumber(){return STUDENTNUMBER;}
    public int getContactNumber(){return CONTACTNUMBER;}
    public String getBirthday(){return BIRTHDAY;}
    
    private Student(StudentBuilder builder){
        this.FIRSTNAME = builder.FIRSTNAME;
        this.MIDDLENAME = builder.MIDDLENAME;
        this.LASTNAME = builder.LASTNAME;
        this.AGE = builder.AGE;
        this.YEARLEVEL = builder.AGE;
        this.COURSE = builder.COURSE;
        this.SECTION = builder.SECTION;
        this.STUDENTNUMBER = builder.STUDENTNUMBER;
        this.CONTACTNUMBER = builder.CONTACTNUMBER;
        this.BIRTHDAY = builder.BIRTHDAY;
    }

    public static class StudentBuilder {
        private String FIRSTNAME;
        private String MIDDLENAME;
        private String LASTNAME;
        private int AGE;
        private int YEARLEVEL;
        private String COURSE;
        private int SECTION;
        private int STUDENTNUMBER;
        private int CONTACTNUMBER;
        private String BIRTHDAY;

        public StudentBuilder setFirstName(String firstName){
            this.FIRSTNAME = firstName;
            return this; }

        public StudentBuilder setMiddleName(String middleName){
            this.MIDDLENAME = middleName;
            return this; }

        public StudentBuilder setLastName(String lastName){
            this.LASTNAME = lastName;
            return this; }

        public StudentBuilder setAge(int age){
            this.AGE = age;
            return this; }

        public StudentBuilder setYearLevel(int yearLevel){
            this.YEARLEVEL = yearLevel;
            return this; }

        public StudentBuilder setCourse(String course){
            this.COURSE = course;
            return this; }

        public StudentBuilder setSection(int section){
            this.SECTION = section;
            return this; }

        public StudentBuilder setStudentNumber(int studentNumber){
            this.STUDENTNUMBER = studentNumber;
            return this; }

        public StudentBuilder setContactNumber(int contactNumber){
            this.CONTACTNUMBER = contactNumber;
            return this; }

        public StudentBuilder setBirthday(String birthday){
            this.BIRTHDAY = birthday;
            return this; }

        public Student build(){
            return  new Student(this);
        }
    }

}
