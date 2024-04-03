import java.util.List;

public class Main {
    public static void main(String[] args) {
        JdbcDatabaseOperations jdbcOperations = new JdbcDatabaseOperations();

        testGetMemberById(jdbcOperations);
        testGetAllMembers(jdbcOperations);
    }

    private static void testGetMemberById(JdbcDatabaseOperations jdbcOperations) {
        System.out.println("Testing getMemberById:");
        Member member = jdbcOperations.getMemberById(1);
        System.out.println(member);
    }

    private static void testGetAllMembers(JdbcDatabaseOperations jdbcOperations) {
        System.out.println("Testing getAllMembers:");
        List<Member> members = jdbcOperations.getAllMembers();
        for (Member member : members) {
            System.out.println(member);
        }
    }
}

