package net.danielgill.ros.timetable.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of data templates, and also allows the user to add their own data templates.
 * @author Daniel Gill
 */
public class DataTemplates {
    public static List<DataTemplate> templates;
    
    /**
     * Adds a data template created from the keyword and data points.
     * @param keyword
     * @param maxSpeed
     * @param mass
     * @param maxBrake
     * @param power 
     */
    public void addTemplate(String keyword, int maxSpeed, int mass, int maxBrake, int power) {
        templates.add(new DataTemplate(keyword, maxSpeed, mass, maxBrake, power));
    }
    
    /**
     * Adds a data template and returns the instance of it.
     * @param keyword
     * @param maxSpeed
     * @param mass
     * @param maxBrake
     * @param power
     * @return The instance of the data template.
     */
    private DataTemplate addTemplateR(String keyword, int maxSpeed, int mass, int maxBrake, int power) {
        DataTemplate dt = new DataTemplate(keyword, maxSpeed, mass, maxBrake, power);
        templates.add(dt);
        return dt;
    }
    
    /**
     * Adds a data template from a data template instance. Will override if a template already exists
     * @param dt 
     */
    public void addTemplate(DataTemplate dt) {
        int exists = getIndexOfTemplate(dt.getKeyword());
        if(exists != -1) {
            templates.set(exists, dt);
        } else {
            templates.add(dt);
        }
    }
    
    /**
     * Returns the data template given a specific keyword.
     * @param keyword The keyword to be searched for.
     * @return DataTemplate
     */
    public DataTemplate getTemplate(String keyword) {
        for(int i = 0; i < templates.size(); i++) {
            if(templates.get(i).keywordEqual(keyword)) {
                return templates.get(i);
            }
        }
        return null;
    }

    /**
     * Returns the index of a given data template.
     * @param keyword The template keyword.
     * @return The index in the arraylist.
     */
    private int getIndexOfTemplate(String keyword) {
        for(int i = 0; i < templates.size(); i++) {
            if(templates.get(i).keywordEqual(keyword)) {
                return i;
            }
        }
        return -1;
    }
    
    public DataTemplates() {
        templates = new ArrayList<>();
        C139_1 = addTemplateR("C139_1", 64, 13, 7, 64);
        C142_2 = addTemplateR("C142_2", 121, 56, 11, 336);
        C143_0_2 = addTemplateR("C143/0_2", 121, 56, 11, 336);
        C143_3_2 = addTemplateR("C143/3_2", 121, 56, 11, 336);
        C143_6_2 = addTemplateR("C143/6_2", 121, 56, 11, 336);
        C144_2 = addTemplateR("C144_2", 121, 54, 13, 336);
        C144_3 = addTemplateR("C144_3", 121, 81, 13, 503);
        C150_0_3 = addTemplateR("C150/0_3", 121, 101, 34, 638);
        C150_1_2 = addTemplateR("C150/1_2", 121, 85, 28, 425);
        C150_2_2 = addTemplateR("C150/2_2", 121, 83, 28, 425);
        C150_9_3 = addTemplateR("C150/9_3", 121, 124, 21, 638);
        C153_1 = addTemplateR("C153_1", 121, 46, 9, 213);
        C155_2 = addTemplateR("C155_2", 121, 87, 44, 425);
        C156_2 = addTemplateR("C156_2", 121, 87, 44, 425);
        C158_2 = addTemplateR("C158_2", 145, 94, 47, 522);
        C158_3 = addTemplateR("C158_3", 145, 141, 71, 783);
        C159_3 = addTemplateR("C159_3", 145, 128, 64, 895);
        C165_0_2 = addTemplateR("C165/0_2", 121, 90, 30, 522);
        C165_0_3 = addTemplateR("C165/0_3", 121, 134, 45, 783);
        C165_1_2 = addTemplateR("C165/1_2", 145, 83, 28, 522);
        C165_1_3 = addTemplateR("C165/1_3", 145, 124, 41, 783);
        C166_3 = addTemplateR("C166_3", 145, 134, 45, 783);
        C168_3 = addTemplateR("C168_3", 161, 148, 49, 1259);
        C168_4 = addTemplateR("C168_4", 161, 197, 66, 1259);
        C170_1_2 = addTemplateR("C170/1_2", 161, 101, 34, 629);
        C170_1_3 = addTemplateR("C170/1_3", 161, 148, 49, 944);
        C170_2_2 = addTemplateR("C170/2_2", 161, 99, 33, 629);
        C170_2_3 = addTemplateR("C170/2_3", 161, 148, 49, 944);
        C170_3_3 = addTemplateR("C170/3_3", 161, 148, 49, 629);
        C170_4_3 = addTemplateR("C170/4_3", 161, 148, 49, 944);
        C170_5_2 = addTemplateR("C170/5_2", 161, 99, 33, 944);
        C170_6_3 = addTemplateR("C170/6_3", 161, 148, 49, 944);
        C171_7_2 = addTemplateR("C171/7_2", 161, 108, 36, 629);
        C171_8_4 = addTemplateR("C171/8_4", 161, 197, 66, 944);
        C172_0_2 = addTemplateR("C172/0_2", 161, 92, 31, 722);
        C172_1_2 = addTemplateR("C172/1_2", 161, 92, 31, 722);
        C172_2_2 = addTemplateR("C172/2_2", 161, 94, 31, 722);
        C172_3_3 = addTemplateR("C172/3_3", 161, 134, 45, 722);
        C175_0_2 = addTemplateR("C175/0_2", 161, 110, 37, 671);
        C175_1_3 = addTemplateR("C175/1_3", 161, 165, 55, 1007);
        C180_5 = addTemplateR("C180_5", 201, 280, 70, 2796);
        C185_3 = addTemplateR("C185_3", 161, 185, 92, 1678);
        C220_4 = addTemplateR("C220_4", 201, 228, 76, 2237);
        C221_4 = addTemplateR("C221_4", 201, 255, 85, 2237);
        C221_5 = addTemplateR("C221_5", 201, 314, 105, 2796);
        C222_0_5 = addTemplateR("C222/0_5", 201, 274, 91, 2088);
        C222_0_7 = addTemplateR("C222/0_7", 201, 384, 128, 3915);
        C222_0_4 = addTemplateR("C222/0_4", 201, 220, 73, 2237);
        C195_0_2 = addTemplateR("C195/0_2", 161, 25, 12, 780);
        C195_1_3 = addTemplateR("C195/1_3", 161, 37, 18, 1170);
        C230_2 = addTemplateR("C230_2", 161, 60, 30, 298);
        C230_3 = addTemplateR("C230_3", 161, 91, 45, 298);
        C800_1_5 = addTemplateR("C800/1_5", 201, 272, 136, 4876);
        C800_2_9 = addTemplateR("C800/2_9", 201, 492, 246, 4876);
        C800_1_9 = addTemplateR("C800/1_9", 201, 492, 246, 4876);
        C802_5 = addTemplateR("C802_5", 201, 273, 137, 4876);
        C68_5 = addTemplateR("C68_5", 161, 241, 80, 2834);
        C67_4 = addTemplateR("C67_4", 161, 148, 37, 1864);
        C67_6 = addTemplateR("C67_6", 161, 222, 55, 1864);
        C43_4 = addTemplateR("C43_4", 201, 157, 35, 984);
        C43_6 = addTemplateR("C43_6", 201, 222, 35, 984);
        C43_7 = addTemplateR("C43_7", 201, 259, 35, 984);
        C43_8 = addTemplateR("C43_8", 201, 296, 35, 984);
        C43_9 = addTemplateR("C43_9", 201, 333, 35, 984);
        
        C90_9 = addTemplateR("C90_9", 161, 333, 40, 3728);
        C91_9 = addTemplateR("C91_9", 201, 403, 81, 4832);
        C313_0_3 = addTemplateR("C313/0_3", 121, 104, 35, 656);
        C313_1_3 = addTemplateR("C313/1_3", 121, 104, 35, 656);
        C313_2_3 = addTemplateR("C313/2_3", 121, 104, 35, 656);
        C314_2_3 = addTemplateR("C314/2_3", 121, 111, 37, 656);
        C315_8_3 = addTemplateR("C315/8_3", 121, 111, 37, 656);
        C317_1_4 = addTemplateR("C317/1_4", 161, 130, 32, 990);
        C317_5_4 = addTemplateR("C317/5_4", 161, 130, 32, 990);
        C317_6_4 = addTemplateR("C317/6_4", 161, 130, 32, 990);
        C317_7_4 = addTemplateR("C317/7_4", 161, 130, 32, 990);
        C317_8_4 = addTemplateR("C317/8_4", 161, 130, 32, 990);
        C318_3 = addTemplateR("C318_3", 145, 97, 24, 1072);
        C319_4 = addTemplateR("C319_4", 161, 152, 30, 1072);
        C320_3_3 = addTemplateR("C320/3_3", 145, 101, 40, 1072);
        C321_3_4 = addTemplateR("C321/3_4", 161, 134, 45, 1072);
        C321_4_4 = addTemplateR("C321/4_4", 161, 134, 45, 1072);
        C322_4 = addTemplateR("C322_4", 161, 130, 43, 1072);
        C323_3 = addTemplateR("C323_3", 145, 134, 67, 1168);
        C325_4 = addTemplateR("C325_4", 161, 143, 72, 1072);
        C331_0_3 = addTemplateR("C331/0_3", 161, 104, 90, 780);
        C331_1_4 = addTemplateR("C331/1_4", 161, 139, 93, 1170);
        C332_4 = addTemplateR("C332_4", 161, 202, 67, 1400);
        C332_5 = addTemplateR("C332_5", 161, 252, 84, 1400);
        C333_4 = addTemplateR("C333_4", 161, 211, 70, 1400);
        C334_3 = addTemplateR("C334_3", 145, 138, 69, 1080);
        C345_9 = addTemplateR("C345_9", 145, 292, 195, 1678);
        C350_1_4 = addTemplateR("C350/1_4", 177, 202, 81, 1000);
        C350_2_4 = addTemplateR("C350/2_4", 161, 202, 81, 1000);
        C350_3_4 = addTemplateR("C350/3_4", 161, 202, 81, 1000);
        C350_4_4 = addTemplateR("C350/4_4", 161, 202, 81, 1000);
        C357_4 = addTemplateR("C357_4", 161, 157, 63, 1499);
        C360_1_4 = addTemplateR("C360/1_4", 161, 166, 66, 1000);
        C360_5_5 = addTemplateR("C360/5_5", 161, 207, 83, 1000);
        C365_4 = addTemplateR("C365_4", 161, 152, 76, 937);
        C373_16 = addTemplateR("C373_16", 299, 806, 161, 12229);
        C373_20 = addTemplateR("C373_20", 299, 1008, 202, 12229);
        C375_3 = addTemplateR("C375_3", 161, 148, 99, 1000);
        C375_4 = addTemplateR("C375_4", 163, 197, 131, 1500);
        C376_5 = addTemplateR("C376_5", 164, 202, 134, 2000);
        C377_1_4 = addTemplateR("C377/1_4", 166, 184, 122, 1500);
        C377_2_4 = addTemplateR("C377/2_4", 169, 179, 119, 1500);
        C377_3_3 = addTemplateR("C377/3_3", 171, 134, 90, 1000);
        C377_4_4 = addTemplateR("C377/4_4", 172, 179, 119, 1500);
        C377_5_4 = addTemplateR("C377/5_4", 174, 179, 119, 1500);
        C377_6_5 = addTemplateR("C377/6_5", 175, 224, 149, 1500);
        C377_7_5 = addTemplateR("C377/7_5", 177, 224, 149, 1500);
        C378_0_3 = addTemplateR("C378/0_3", 121, 134, 90, 1500);
        C378_0_4 = addTemplateR("C378/0_4", 121, 179, 119, 1500);
        C378_1_5 = addTemplateR("C378/1_5", 121, 224, 149, 1500);
        C378_2_7 = addTemplateR("C378/2_7", 121, 314, 209, 1500);
        C379_4 = addTemplateR("C379_4", 161, 179, 119, 1200);
        C380_0_3 = addTemplateR("C380/0_3", 161, 144, 96, 1000);
        C380_1_4 = addTemplateR("C380/1_4", 161, 193, 128, 1000);
        C387_1_4 = addTemplateR("C387/1_4", 177, 179, 119, 1500);
        C387_2_4 = addTemplateR("C387/2_4", 177, 179, 119, 1500);
        C387_3_4 = addTemplateR("C387/3_4", 177, 179, 119, 1500);
        C390_0_9 = addTemplateR("C390/0_9", 201, 524, 403, 4876);
        C390_1_11 = addTemplateR("C390/1_11", 201, 641, 427, 4876);
        C395_6 = addTemplateR("C395_6", 225, 309, 206, 1680);
        C399_3 = addTemplateR("C399_3", 48, 71, 35, 869);
        C442_5 = addTemplateR("C442_5", 161, 235, 118, 1199);
        C444_5 = addTemplateR("C444_5", 161, 224, 112, 2000);
        C450_4 = addTemplateR("C450_4", 161, 197, 99, 1998);
        C455_4 = addTemplateR("C455_4", 121, 148, 49, 746);
        C456_2 = addTemplateR("C456_2", 121, 78, 20, 373);
        C458_0_4 = addTemplateR("C458/0_4", 161, 179, 90, 1620);
        C458_5_5 = addTemplateR("C458/5_5", 121, 235, 118, 1620);
        C460_8 = addTemplateR("C460_8", 161, 358, 119, 2699);
        C465_4 = addTemplateR("C465_4", 121, 148, 37, 2240);
        C466_2 = addTemplateR("C466_2", 121, 74, 18, 1119);
        C482_2 = addTemplateR("C482_2", 100, 47, 19, 344);
        C483_3 = addTemplateR("C483_3", 72, 91, 30, 354);
        C507_3 = addTemplateR("C507_3", 121, 108, 36, 656);
        C508_3 = addTemplateR("C508_3", 121, 108, 36, 656);
        C700_8 = addTemplateR("C700_8", 161, 305, 152, 3300);
        C707_5 = addTemplateR("C707_5", 161, 202, 101, 1200);
        C717_6 = addTemplateR("C717_6", 161, 228, 114, 895);
        C801_5 = addTemplateR("C801_5", 201, 1148, 574, 4876);
        C801_9 = addTemplateR("C801_9", 201, 3720, 1860, 4876);
    }
    
    public DataTemplate C139_1;
    public DataTemplate C142_2;
    public DataTemplate C143_0_2;
    public DataTemplate C143_3_2;
    public DataTemplate C143_6_2;
    public DataTemplate C144_2;
    public DataTemplate C144_3;
    public DataTemplate C150_0_3;
    public DataTemplate C150_1_2;
    public DataTemplate C150_2_2;
    public DataTemplate C150_9_3;
    public DataTemplate C153_1;
    public DataTemplate C155_2;
    public DataTemplate C156_2;
    public DataTemplate C158_2;
    public DataTemplate C158_3;
    public DataTemplate C159_3;
    public DataTemplate C165_0_2;
    public DataTemplate C165_0_3;
    public DataTemplate C165_1_2;
    public DataTemplate C165_1_3;
    public DataTemplate C166_3;
    public DataTemplate C168_3;
    public DataTemplate C168_4;
    public DataTemplate C170_1_2;
    public DataTemplate C170_1_3;
    public DataTemplate C170_2_2;
    public DataTemplate C170_2_3;
    public DataTemplate C170_3_3;
    public DataTemplate C170_4_3;
    public DataTemplate C170_5_2;
    public DataTemplate C170_6_3;
    public DataTemplate C171_7_2;
    public DataTemplate C171_8_4;
    public DataTemplate C172_0_2;
    public DataTemplate C172_1_2;
    public DataTemplate C172_2_2;
    public DataTemplate C172_3_3;
    public DataTemplate C175_0_2;
    public DataTemplate C175_1_3;
    public DataTemplate C180_5;
    public DataTemplate C185_3;
    public DataTemplate C220_4;
    public DataTemplate C221_4;
    public DataTemplate C221_5;
    public DataTemplate C222_0_5;
    public DataTemplate C222_0_7;
    public DataTemplate C222_0_4;
    public DataTemplate C195_0_2;
    public DataTemplate C195_1_3;
    public DataTemplate C196_0_2;
    public DataTemplate C196_1_4;
    public DataTemplate C197_0_2;
    public DataTemplate C197_1_3;
    public DataTemplate C230_2;
    public DataTemplate C230_3;
    public DataTemplate C231_4;
    public DataTemplate C755_3_3;
    public DataTemplate C755_4_4;
    public DataTemplate C756_3_3;
    public DataTemplate C756_4_4;
    public DataTemplate C800_1_5;
    public DataTemplate C800_2_9;
    public DataTemplate C800_1_9;
    public DataTemplate C802_5;
    public DataTemplate C68_5;
    public DataTemplate C67_4;
    public DataTemplate C67_6;
    public DataTemplate C43_4;
    public DataTemplate C43_6;
    public DataTemplate C43_7;
    public DataTemplate C43_8;
    public DataTemplate C43_9;
    
    public DataTemplate C90_9;
    public DataTemplate C91_9;
    public DataTemplate C313_0_3;
    public DataTemplate C313_1_3;
    public DataTemplate C313_2_3;
    public DataTemplate C314_2_3;
    public DataTemplate C315_8_3;
    public DataTemplate C317_1_4;
    public DataTemplate C317_5_4;
    public DataTemplate C317_6_4;
    public DataTemplate C317_7_4;
    public DataTemplate C317_8_4;
    public DataTemplate C318_3;
    public DataTemplate C319_4;
    public DataTemplate C320_3_3;
    public DataTemplate C321_3_4;
    public DataTemplate C321_4_4;
    public DataTemplate C322_4;
    public DataTemplate C323_3;
    public DataTemplate C325_4;
    public DataTemplate C331_0_3;
    public DataTemplate C331_1_4;
    public DataTemplate C332_4;
    public DataTemplate C332_5;
    public DataTemplate C333_4;
    public DataTemplate C334_3;
    public DataTemplate C345_9;
    public DataTemplate C350_1_4;
    public DataTemplate C350_2_4;
    public DataTemplate C350_3_4;
    public DataTemplate C350_4_4;
    public DataTemplate C357_4;
    public DataTemplate C360_1_4;
    public DataTemplate C360_5_5;
    public DataTemplate C365_4;
    public DataTemplate C373_16;
    public DataTemplate C373_20;
    public DataTemplate C374_16;
    public DataTemplate C375_3;
    public DataTemplate C375_4;
    public DataTemplate C376_5;
    public DataTemplate C377_1_4;
    public DataTemplate C377_2_4;
    public DataTemplate C377_3_3;
    public DataTemplate C377_4_4;
    public DataTemplate C377_5_4;
    public DataTemplate C377_6_5;
    public DataTemplate C377_7_5;
    public DataTemplate C378_0_3;
    public DataTemplate C378_0_4;
    public DataTemplate C378_1_5;
    public DataTemplate C378_2_7;
    public DataTemplate C379_4;
    public DataTemplate C380_0_3;
    public DataTemplate C380_1_4;
    public DataTemplate C385_3;
    public DataTemplate C385_4;
    public DataTemplate C387_1_4;
    public DataTemplate C387_2_4;
    public DataTemplate C387_3_4;
    public DataTemplate C390_0_9;
    public DataTemplate C390_1_11;
    public DataTemplate C395_6;
    public DataTemplate C397_5;
    public DataTemplate C398_3;
    public DataTemplate C399_3;
    public DataTemplate C442_5;
    public DataTemplate C444_5;
    public DataTemplate C450_4;
    public DataTemplate C455_4;
    public DataTemplate C456_2;
    public DataTemplate C458_0_4;
    public DataTemplate C458_5_5;
    public DataTemplate C460_8;
    public DataTemplate C465_4;
    public DataTemplate C466_2;
    public DataTemplate C482_2;
    public DataTemplate C483_3;
    public DataTemplate C507_3;
    public DataTemplate C508_3;
    public DataTemplate C700_8;
    public DataTemplate C701_12;
    public DataTemplate C707_5;
    public DataTemplate C710_1_4;
    public DataTemplate C710_2_4;
    public DataTemplate C711_10;
    public DataTemplate C717_6;
    public DataTemplate C720_5;
    public DataTemplate C720_10;
    public DataTemplate C769_4;
    public DataTemplate C777_4;
    public DataTemplate C801_5;
    public DataTemplate C801_9;

}
