package pages;

import com.fasterxml.jackson.databind.node.ArrayNode;
import io.AllTypesOfPrint;
import io.Users;

import java.util.*;

public class Listofpages {
    private List<Pagesbackforward> pageslist = new ArrayList<>();

    private AllTypesOfPrint all = new AllTypesOfPrint();

    /**
     *
     * @param page
     */
    //adăugăm în "stivă" noua pagină
    //n-am dorit o stivă efectivă, am vrut să implementez eu push și pop :)
    public void addPage(final Pages page) {
        CurrentPage newcurrentpage = new CurrentPage();
        newcurrentpage.setCurpage(page);
        Pagesbackforward newpage = new Pagesbackforward(newcurrentpage);
        if (pageslist.size() <= 1) {
            pageslist.add(newpage);
        } else {
            if (page.getName() != null) {
                if (!pageslist.get(pageslist.size() - 1).getCurrentpage().getCurpage().
                        getName().equals(newpage.getCurrentpage().getCurpage().getName())) {
                    pageslist.add(newpage);
                }
            }
        }
    }

    /**
     *
     */
    //cand ajungem pe homepagena golim vectorul
    public void clearPages() {
        pageslist.clear();
    }

    /**
     *
     * @param output
     * @return
     */
    public Pages changewithlast(ArrayNode output) {
          Pagesbackforward curpage;
          if (pageslist.size() > 1) {
              curpage = pageslist.get(pageslist.size() - 1);
              if (curpage.execute(pageslist.get(pageslist.size() - 2).
                      getCurrentpage().getCurpage())) {
                  pageslist.remove(pageslist.size() - 1);
              } else {
                  output = all.printUser(output, new Users(), 0);
              }
          }
        return pageslist.get(pageslist.size() - 1).getCurrentpage().getCurpage();
    }
}
