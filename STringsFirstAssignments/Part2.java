
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    String findSimpleGene(String dna, String startCodon, String stopCodon) {
        if (dna.contains("A") || dna.contains("T") || dna.contains("G") || dna.contains("C")) {
            startCodon = startCodon.toUpperCase();
            stopCodon = stopCodon.toUpperCase();
        }
        else {
            startCodon = startCodon.toLowerCase();
            stopCodon = stopCodon.toLowerCase();
        }
        int start = dna.indexOf(startCodon);
        if (start == -1) {
            return "";
        }
        int stop = dna.indexOf(stopCodon);
        if (stop == -1) {
            return "";
        }
        String gene = dna.substring(start, stop+3);
        return gene;
    }
    void testSimpleGene() {
        String gene1 = "TATGCGTAGTCGAGCCTAAGATCA";
        System.out.println("DNA strand: " + gene1.toLowerCase());
        System.out.println("Gene: " + findSimpleGene(gene1.toLowerCase(), "ATG", "TAA"));
        String gene2 = "GTACAGTAGTCCAGATAAAGC";
        System.out.println("DNA strand: " + gene2.toLowerCase());
        System.out.println("Gene: " + findSimpleGene(gene2.toLowerCase(), "ATG", "TAA"));
        String gene3 = "GCTGATGCGAGTAGTCGCACGA";
        System.out.println("DNA strand: " + gene3);
        System.out.println("Gene: " + findSimpleGene(gene3, "ATG", "TAA"));
        String gene4 = "TCGACGTATGGATCGATGCCGAGATAA";
        System.out.println("DNA strand: " + gene4);
        System.out.println("Gene: " + findSimpleGene(gene4, "atg", "TAA"));
        String gene5 = "GCTAGCTGGAGAGGGAAAACTCTCTAGGAT";
        System.out.println("DNA strand: " + gene5);
        System.out.println("Gene: " + findSimpleGene(gene5, "ATG", "TAA"));
    }
}
