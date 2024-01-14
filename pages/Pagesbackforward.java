package pages;

public class Pagesbackforward implements Goback {
    private CurrentPage curpage;

    public Pagesbackforward(final CurrentPage curpage) {
        this.curpage = curpage;
    }

    /**
     *
     * @param page
     * @return
     */
    public boolean execute(final Pages page) {
        if (page.getName().equals("Login") || page.getName().equals("Register")) {
            return false;
        } else {
            curpage.setCurpage(page);
            return true;
        }
    }

    /**
     *
     * @return
     */
    public CurrentPage getCurrentpage() {
        return curpage;
    }

}
