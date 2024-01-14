package pages;

public interface visitor {
    /**
     *
     * @param page
     */
    void visit(Homepagena page);

    /**
     *
     * @param page
     */
    void visit(Homepagea page);

    /**
     *
     * @param page
     */
    void visit(Login page);

    /**
     *
     * @param page
     */
    void visit(Register page);

    /**
     *
     * @param page
     */
    void visit(Seedetails page);

    /**
     *
     * @param page
     */
    void visit(Movies page);

    /**
     *
     * @param page
     */
    void visit(Upgrades page);
}
