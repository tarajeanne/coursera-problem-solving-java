
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class Part1 {
    int findStopCodon(String dna, int startIndex, String stopCodon) {
        dna = dna.toUpperCase();
        stopCodon = stopCodon.toUpperCase();
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
        dna = dna.toUpperCase();
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
        dna = dna.toUpperCase();
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
        dna = dna.toUpperCase();
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
        dna = dna.toUpperCase();
        double cCount = 0.0;
        double gCount = 0.0;
        for (char c: dna.toCharArray()) {
            if (c == 'C') {
                cCount ++;
            }
            else if (c == 'G') {
                gCount ++;
            }
        }
        return (cCount + gCount) / dna.length();
    }
    
    void processGenes(StorageResource sr) {
        int overNineCount = 0;
        int cgRatioCount = 0;
        int longestLength = 0;
        for (String gene: sr.data()) {
            if(gene.length() > 60) {
                System.out.println("A gene over 60 characters! " + gene);
                overNineCount ++;
            }
            if(cgRatio(gene) > .35) {
                System.out.println("This one has a lot of c's and g's! " + gene);
                cgRatioCount ++;
            }
            if (gene.length() > longestLength) {
                longestLength = gene.length();
            }
        }
        System.out.println("There were " + sr.size() + " genes in this DNA strand.");
        System.out.println("There were " + overNineCount + " genes over 60 chars.");
        System.out.println("There were " + cgRatioCount + " genes with a cg ratio > .35");
        System.out.println("The longest gene was " + longestLength + ".");
    }
    
    void testProcessGenes() {
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        StorageResource testGenes = getAllGenes(dna.toUpperCase());
        processGenes(testGenes);
    }
    
    void testGetAllGenes() {
        StorageResource genes = getAllGenes("zzATGzzzzzzzzzzzzTAGzzzzzzATGzzTAAzzzzzzzTGAzATGzzzzzzzzzTAA");
        for (String gene : genes.data()) {
            System.out.println(gene);
        }
        
        System.out.println(cgRatio("abcdefgabcdefgabcdefgabcdefg"));
    }
}
