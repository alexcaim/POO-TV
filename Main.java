
import io.AllTypesOfPrint;
import io.Movie;
import io.Input;
import pages.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.ArrayNode;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public final class Main {
    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(final String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode output = objectMapper.createArrayNode();
        int i, error, currentuser = 0;
        ArrayList<Movie> movieslist = new ArrayList<>();
        Input input = objectMapper.readValue(new File(args[0]), Input.class);
        Listofpages listofpages = new Listofpages();
        AllTypesOfPrint all = new AllTypesOfPrint();
        //pagina de pornire este mereu de tip Homepagena
        //următoarele sunt de tip Pages

        Homepagena page = new Homepagena();
        Onpage onpageactions = new Onpage(input);
        Changepage changepageactions = new Changepage(input);
        onpageactions.setOutput(output);
        listofpages.addPage(page);

        page.accept(changepageactions);
        output = onpageactions.getOutput();
        Pages newpage = changepageactions.getPagereturn();

        for (i = 1; i < input.getActions().size(); i++) {

            newpage.setMoviesavaibleonpage(movieslist);
            listofpages.addPage(newpage);
            if (newpage.getName().equals("Homepagena")) {
                listofpages.clearPages();
            }
            if (input.getActions().get(i).getType().equals("back")) {
                newpage = listofpages.changewithlast(output);
                movieslist = newpage.getMoviesavaibleonpage();
                if (newpage.getName().equals("Movies")) {
                    output = all.printMovies(output, input.getUsers().get(currentuser),
                            movieslist, 1);
                }
            } else if (input.getActions().get(i).getType().equals("database")) {
                MovieSubject moviesubject;
                if (input.getActions().get(i).getFeature().equals("add")) {
                    moviesubject = new MovieSubject(input,
                            input.getActions().get(i).getAddedMovie());
                    moviesubject.setOutput(output);
                    ObserverNewMovie observeradd = new ObserverNewMovie(moviesubject);
                    moviesubject.addobserver(observeradd);
                    moviesubject.changemade();
                    output = moviesubject.getOutput();
                } else {
                    int find = 0;
                    for (Movie movie : input.getMovies()) {
                        if (movie.getName().equals(input.getActions().get(i).getDeletedMovie())) {
                            moviesubject = new MovieSubject(input, movie);
                            ObserverDelMovie observerdel = new ObserverDelMovie(moviesubject);
                            moviesubject.addobserver(observerdel);
                            moviesubject.changemade();
                            find = 1;
                            break;
                        }
                    }
                    if (find == 0) {
                        output = all.printUser(output, input.getUsers().get(0), 0);
                    }
                }
            } else {
                onpageactions.setIndexaction(i);
                onpageactions.setInput(input);
                onpageactions.setOutput(output);
                onpageactions.setMoviesavaible(movieslist);
                newpage.accept(onpageactions);
                output = onpageactions.getOutput();
                input = onpageactions.getInput();
                error = onpageactions.getError();
                currentuser = onpageactions.getCurrentuser();
                movieslist = onpageactions.getMoviesavaible();

                //memorăm legăturile dintre cei doi visitor și
                //conferim fiecăruia câmpurile necesare
                changepageactions.setPagereturn(newpage);
                changepageactions.setIndexaction(i);
                changepageactions.setCurrentuser(currentuser);
                changepageactions.setOutput(output);
                changepageactions.seterror(error);
                changepageactions.setMoviesavaible(movieslist);
                newpage.accept(changepageactions);
                output = changepageactions.getOutput();
                movieslist = changepageactions.getMoviesavaible();

                //urmează mutarea dacă este cazul pe altă pagină

                newpage = changepageactions.getPagereturn();
            }
        }

        //verificăm dacă ultima comandă este de tip change page, caz în
        //care pagina nu ajunge să fie vizitată onpage și astfel repetăm
        //incă o data procedeul

        if (input.getActions().get(i - 1).getType().equals("change page")) {
            onpageactions.setIndexaction(i - 1);
            onpageactions.setInput(input);
            onpageactions.setOutput(output);
            onpageactions.setMoviesavaible(movieslist);
            newpage.accept(onpageactions);
        }

        if (input.getUsers().get(currentuser).getCredentials().getAccountType().equals("premium")
                && !newpage.getName().equals("Homepagena")) {
            ObserverRecommendation oR = new ObserverRecommendation(input, output,
                input.getUsers().get(currentuser));
            oR.update();
            output = oR.getOutput();
        }
        ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
        objectWriter.writeValue(new File(args[1]), output);
    }
}
