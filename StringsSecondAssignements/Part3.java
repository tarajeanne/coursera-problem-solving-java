
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
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
    
    int countGenes(String dna) {
        int count = 0;
        while(true) {
            String gene = findGene(dna);
            if (gene.isEmpty()) {
                break;
            }
            count++;
            dna = dna.substring(dna.indexOf(gene) + gene.length());
            
        }
        return count;
    }
    
    void testCountGenes() {
        System.out.println(countGenes("ATGTAAGATGCCCTAGT"));
        System.out.println(countGenes("zzzzzzzzzzz"));
        System.out.println(countGenes("zzATGzzzzzzTAGzzATGzzTAAzTAGATGzzzzzzTGA"));
    }
}
