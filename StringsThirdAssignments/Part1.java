
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part1 {
    int findStopCodon(String dna, int startIndex, String stopCodon) {
        int codonIndex = -1;
        int tempStart = startIndex;
        while (codonIndex == -1) {
            int tempIndex = dna.indexOf(stopCodon, tempStart);
            if (tempIndex == -1) {
                codonIndex = dna.length();
            }
            else if ((tempIndex-startIndex) % 3 == 0) {
                codonIndex = tempIndex;
            }
            else {
                tempStart = tempIndex + 3;
            }
        }
        return codonIndex;
    }
    
    String findGene(String dna) {
        int startIndex = dna.indexOf("ATG");
        if (startIndex == -1) {
            return "";
        }
        int TAAIndex = findStopCodon(dna, startIndex, "TAA");
        int TAGIndex = findStopCodon(dna, startIndex, "TAG");
        int TGAIndex = findStopCodon(dna, startIndex, "TGA");
        
        int min = Math.min(TAAIndex, TAGIndex);
        int minmin = Math.min(min, TGAIndex);
        
        if (minmin == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minmin+3);
    }
    
    void printAllGenes(String dna) {
        while(true) {
            String gene = findGene(dna);
            if (gene.isEmpty()) {
                break;
            }
            System.out.println(gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length());
            
        }
    }
    
    StorageResource getAllGenes(String dna) {
        StorageResource genes = new StorageResource();
        while(true) {
            String gene = findGene(dna);
            if (gene.isEmpty()) {
                break;
            }
            genes.add(gene);
            dna = dna.substring(dna.indexOf(gene) + gene.length());
        }
        return genes;
    }
    
    void testFindStopCodon() {
        System.out.println(findStopCodon("ATGzzzzzzzzzTAA", 0, "TAA"));
        System.out.println(findStopCodon("ATGzzzzzTAA", 0, "TAA"));
    }
    
    void testFindGene() {
        System.out.println(findGene("zzATGzzzzzzzzzzzzTAGzzz"));
        System.out.println(findGene("zzzATGzzTAAzzzzzzzTGAz"));
        System.out.println(findGene("zzzzzzzZZzzzzzzz"));
        System.out.println(findGene("ATGzzzzzzzzzzzzzz"));
    }
    
    double cgRatio(String dna) {
        dna = dna.toLowerCase();
        double cCount = 0.0;
        double gCount = 0.0;
        for (char c: dna.toCharArray()) {
            if (c == 'c') {
                cCount ++;
            }
            else if (c == 'g') {
                gCount ++;
            }
        }
        return (cCount + gCount) / dna.length();
    }
    
    void testGetAllGenes() {
        StorageResource genes = getAllGenes("zzATGzzzzzzzzzzzzTAGzzzzzzATGzzTAAzzzzzzzTGAzATGzzzzzzzzzTAA");
        for (String gene : genes.data()) {
            System.out.println(gene);
        }
        
        System.out.println(cgRatio("abcdefgabcdefgabcdefgabcdefg"));
    }
}
