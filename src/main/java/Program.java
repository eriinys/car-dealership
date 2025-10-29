public class Program {
    public static void main(String[] args) {
        UserInterface userInterface = new UserInterface();
        DealershipFileManager fileManager = new DealershipFileManager();

        fileManager.getDealership();
        userInterface.display();

    }
}
