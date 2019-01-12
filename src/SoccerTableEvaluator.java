import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class SoccerTableEvaluator {
    private String filename = "";
    private String header = "";

    private BufferedReader bufferedReader;
    private FileReader fileReader;

    SoccerTableEvaluator(String filename)
    {
        setFilename(filename);
        setHeader();
    }

    private void initReader()
    {
        try {
            fileReader = new FileReader(filename);
            bufferedReader = new BufferedReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private int findHeaderAttributPositionFromName(String name)
    {
       int startPosition;
       startPosition = header.indexOf(name);

       if (startPosition <= 0)
       {
           throw new RuntimeException();
       }

       return startPosition;
    }

    private boolean isDigit(char number)
    {
        String digits = "1234567890";
        String tmp = "";
        tmp += number + "\0";
        return digits.contains(tmp.trim());
    }

    String getTeamByPosition(String line, int positon)
    {
        int my_pos = positon;
        char c;
        StringBuilder teamname = new StringBuilder();
        while (((c = line.charAt(my_pos)) != ' '))
        {
            teamname.append(c);
            my_pos++;
        }
        return teamname.toString();
    }

    String getTeamWithSmallestDifference()
    {
        String line;
        int diff = 9999;
        String team = "";
        try {
            while ((line = bufferedReader.readLine()) != null)
            {
                 int goalPlus;
                 int goalMinus;

                 goalPlus = getNumberByPosition(line, findHeaderAttributPositionFromName("F"));
                 goalMinus = getNumberByPosition(line, findHeaderAttributPositionFromName("A"));

                 int tmp_diff;

                 if ((tmp_diff = (goalPlus-goalMinus)) < diff)
                 {
                     diff = tmp_diff;
                     team = getTeamByPosition(line, findHeaderAttributPositionFromName("Team"));
                 }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return team;
    }

    int getNumberByPosition(String line, int position)
    {
        char three, two, one;

        three = line.charAt(position);
        two = line.charAt(position+1);
        one = line.charAt(position+2);

        String sum = "0";

        if (isDigit(three))
        {
            sum += three;
        }

        if (isDigit(two))
        {
            sum += two;
        }

        if (isDigit(one))
        {
            sum += one;
        }

        return Integer.parseInt(sum.trim());
    }

    private void setFilename(String filename)
    {
        this.filename = filename;
    }

    private String getFilename()
    {
        return this.filename;
    }

    private void setHeader()
    {
        try {
            initReader();
            header = bufferedReader.readLine();
            if (header.equals(""))
            {
                throw new RuntimeException();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
