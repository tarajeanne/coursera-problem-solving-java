
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    String findSimpleGene(String dna) {
        int start = dna.indexOf("ATG");
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf("TAA");
        if (stop == -1) {
            return "";
        }
        String gene = dna.substring(start, stop+3);
        return gene;
    }
    void testSimpleGene() {
        String gene1 = "TATGCGTAGTCGAGCCTAAGATCA";
        System.out.println("DNA strand: " + gene1);
        System.out.println("Gene: " + findSimpleGene(gene1));
        String gene2 = "GTACAGTAGTCCAGATAAAGC";
        System.out.println("DNA strand: " + gene2);
        System.out.println("Gene: " + findSimpleGene(gene2));
        String gene3 = "GCTGATGCGAGTAGTCGCACGA";
        System.out.println("DNA strand: " + gene3);
        System.out.println("Gene: " + findSimpleGene(gene3));
        String gene4 = "TCGACGTATGGATCGATGCCGAGATAA";
        System.out.println("DNA strand: " + gene4);
        System.out.println("Gene: " + findSimpleGene(gene4));
        String gene5 = "GCTAGCTGGAGAGGGAAAACTCTCTAGGAT";
        System.out.println("DNA strand: " + gene5);
        System.out.println("Gene: " + findSimpleGene(gene5));
    }
    public void main (String[] args) {
        testSimpleGene();
    }
}
