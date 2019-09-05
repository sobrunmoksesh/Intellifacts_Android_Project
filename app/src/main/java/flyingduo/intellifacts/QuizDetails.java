package flyingduo.intellifacts;

public class QuizDetails{

    private String[] questions = {"Which of these is a Mauritian drink ?", "Who is considered as the father of the Mauritian nation ?",
            "Which is the folk music of Mauritius?",
            "How many earth colours does Chamarel have ?",
            "Quiz Over!"};

    private String[][] choices = {{"Dholl puri","Alouda","Mine frite"},
            {"Sir Seewoosagur Ramgoolam","Mahe de Labourdonnais","Maurice van Nassau"},
            {"Pop","Rock","Sega"},
            {"2","7","5"},
            {"","",""}};

    public String[] answers = {"Alouda","Sir Seewoosagur Ramgoolam","Sega","7","Unavailable"};

    public String getQuestion(int a){
        String Question = questions[a];
        return Question;
    }

    public String getChoice(int a){
        String choice = choices[a][0];
        return choice;
    }
    public String getChoice1(int a){
        String choice1 = choices[a][1];
        return choice1;
    }
    public String getChoice2(int a){
        String choice2 = choices[a][2];
        return choice2;
    }

    public String getAns(int a){
        String ans = answers[a];
        return ans;
    }

}
