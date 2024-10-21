import java.util.*;

public class Root {
    public static void main(String[] args){
        //init var
        Random rand = new Random();
        boolean exitStatus;
        ArrayList<Integer> win = new ArrayList<Integer>(Arrays.asList(7,11)){};
        ArrayList<Integer>  loss = new ArrayList<Integer>(Arrays.asList(2,3,12)){};
        String message;

        System.out.println("Welcome to the Craps simulator!\nIf the sum is 2, 3, or 12 it is called \"craps\" or \"crapping out\" and the game is over with a loss.\nIf the sum is 7 or 11 it is called a \"natural\" and the game is over with a win.\nFor all other values, the sum becomes \"the point\" and the user makes subsequent rolls until they either roll a 7, in which case they lose, or they roll the point sum in which case they win.");

        //this dowhile is for the entire game looping (so the player can play again) while the whileloop(gameContinueStatus) is for looping if the player doesnt roll the win or loss rolls
        do{
            //init var
            boolean gameContinueStatus = true;
            int iterations = 1;
            int point = 0;
            //this is for the actual game itself. gameContinueStatus is true until the player craps out or wins
            while(gameContinueStatus) {
                //init var
                int sum = 0;

                System.out.println("\n[[[---ROUND-"+iterations+"-BEGIN!--]]]\n"); //header for the start of a roll (this is just so the output looks cleaner and all the rolls arent smushed together)
                //loop for 2 rolls
                for (int i = 1; i <= 2; i++) {
                    int roll = rand.nextInt(6)+1; //adds +11 because rolls start at 0
                    sum += roll;
                    System.out.println("Roll "+i+": "+roll); //ughhh i wanted to make this a printf so it looks better but it wouldnt print on a new line because its a printf and not a println and that makes me very upset
                }
                System.out.println("Sum: " + sum);

                //changes the message the player recieves at the end of the game depending on if they won or lose and stops the loop if autowin/lose
                //it really feels like i couldve done something else here???
                //like maybe a lambda (i still do not get lambdas bro ngl)
                //AHHH I LOOKED IT UP WHAT ARE INTERFACES??? THIS IS BLACK MAGIC LOLLLL

                if(win.contains(sum)||loss.contains(sum)){
                    gameContinueStatus = false; //stops roll-loop and allows player to input if they want to play again

                    if(win.contains(sum)){message = "You win!";
                    }else{message = "You lose!";}

                }else {
                    if (iterations == 1) { //this is literally the only reason the iterations var exists is for this one purpose of making sure the point isnt constantly changed. unless im misunderstanding craps and you're supposed to constantly change the point but that doesnt sound fair
                        point = sum;
                        message = point + " is now the \"point\". You must roll " + point + " or any of the other winning values to win.";
                        win.add(point);
                    }else{message = "Reroll. You must roll " + point + " to win.";}
                }

                System.out.println(message);

                System.out.println("\n[[[---ROUND-"+iterations+"-END!---]]]\n"); //header for the end of rolls (this is just so the output looks cleaner and all the rolls arent smushed together)
                iterations++;
            }
            exitStatus = YNStringCheck();
        }while(exitStatus);
    }

    //used for a [Y/N] prompt, makes sure user inputs [Y/N] or [yes/no] (case-insensitive) before returning false if user input N, and true for Y
    //i stole this from my own code on a different repo btw its just far easier and readable to do this as its own method especially considering i already wrote one
    private static boolean YNStringCheck(){
        //init var
        Scanner scan = new Scanner(System.in);
        boolean check = false;
        final String[] ACCEPTED_VALUES = {"y", "n", "yes", "no"}; //ngl i could probably make this an arraylist to make the if-statement more readable since the only time its called its casted to an arraylist but regular array[] variables have a predefined length so it feels better
        boolean status = true;

        System.out.println("Do you want to play again? [Y/N]");
        do {
            String input = scan.nextLine().toLowerCase();  //input is casted to lowercase so inputs work regardless of case

            if(Arrays.asList(ACCEPTED_VALUES).contains(input)){
                check = true;
                if (input.equals("n") || input.equals("no")) {status = false;}
            }else{System.out.println("Invalid input. Try again.\n"+ "Do you want to play again? [Y/N]");}

        }while(!check);
        return status;
    }
}
