package Chapter23Annotations;


import java.util.List;

public class AnnotationTest {

    @UseCase(id = 47, description =
            "Passwords must contain at least one numeric")
    public boolean validatePassword(String passwd) {
        return (passwd.matches("\\w*\\d\\w*"));
    }
    @UseCase(id = 48)
    public String encryptPassword(String passwd) {
        return new StringBuilder(passwd)
                .reverse().toString();
    }
    @UseCase(id = 49, description =
            "New passwords can't equal previously used ones")
    public boolean checkForNewPassword(
            List<String> prevPasswords, String passwd) {
        return !prevPasswords.contains(passwd);
    }

    public static void main(String[] args) {
        AnnotationTest annotationTest = new AnnotationTest();
        System.out.println(annotationTest.validatePassword("test"));
    }
    
}
