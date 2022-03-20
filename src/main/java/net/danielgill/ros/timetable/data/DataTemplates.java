package net.danielgill.ros.timetable.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains a list of data templates, and also allows the user to add their own data templates.
 * @author Daniel Gill
 */
public class DataTemplates {
    protected static final List<DataTemplate> templates = new ArrayList<>();

    public DataTemplates() {
        //Not implemented.
    }
    
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
    
    private static DataTemplate addTemplateR(String keyword, int maxSpeed, int mass, int maxBrake, int power, String label) {
        DataTemplate dt = new DataTemplate(keyword, maxSpeed, mass, maxBrake, power, label);
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

    /**
     * Returns a list of available data templates.
     * @return The list of data templates.
     */
    public List<DataTemplate> getDataTemplates() {
        return DataTemplates.templates;
    }
    
    public static final DataTemplate C139_1 = addTemplateR("C139_1", 64, 13, 7, 64, "Class 139");
    public static final DataTemplate C142_2 = addTemplateR("C142_2", 121, 56, 11, 336, "Class 142");
    public static final DataTemplate C143_0_2 = addTemplateR("C143/0_2", 121, 56, 11, 336, "Class 143/0");
    public static final DataTemplate C143_3_2 = addTemplateR("C143/3_2", 121, 56, 11, 336, "Class 143/3");
    public static final DataTemplate C143_6_2 = addTemplateR("C143/6_2", 121, 56, 11, 336, "Class 143/6");
    public static final DataTemplate C144_2 = addTemplateR("C144_2", 121, 54, 13, 336, "Class 144");
    public static final DataTemplate C144_3 = addTemplateR("C144_3", 121, 81, 13, 503, "Class 144");
    public static final DataTemplate C150_0_3 = addTemplateR("C150/0_3", 121, 101, 34, 638, "Class 150/0");
    public static final DataTemplate C150_1_2 = addTemplateR("C150/1_2", 121, 85, 28, 425, "Class 150/1");
    public static final DataTemplate C150_2_2 = addTemplateR("C150/2_2", 121, 83, 28, 425, "Class 150/2");
    public static final DataTemplate C150_9_3 = addTemplateR("C150/9_3", 121, 124, 21, 638, "Class 150/9");
    public static final DataTemplate C153_1 = addTemplateR("C153_1", 121, 46, 9, 213, "Class 153");
    public static final DataTemplate C155_2 = addTemplateR("C155_2", 121, 87, 44, 425, "Class 155");
    public static final DataTemplate C156_2 = addTemplateR("C156_2", 121, 87, 44, 425, "Class 156");
    public static final DataTemplate C158_2 = addTemplateR("C158_2", 145, 94, 47, 522, "Class 158");
    public static final DataTemplate C158_3 = addTemplateR("C158_3", 145, 141, 71, 783, "Class 158");
    public static final DataTemplate C159_3 = addTemplateR("C159_3", 145, 128, 64, 895, "Class 159");
    public static final DataTemplate C165_0_2 = addTemplateR("C165/0_2", 121, 90, 30, 522, "Class 165/0");
    public static final DataTemplate C165_0_3 = addTemplateR("C165/0_3", 121, 134, 45, 783, "Class 165/0");
    public static final DataTemplate C165_1_2 = addTemplateR("C165/1_2", 145, 83, 28, 522, "Class 165/1");
    public static final DataTemplate C165_1_3 = addTemplateR("C165/1_3", 145, 124, 41, 783, "Class 165/1");
    public static final DataTemplate C166_3 = addTemplateR("C166_3", 145, 134, 45, 783, "Class 166");
    public static final DataTemplate C168_3 = addTemplateR("C168_3", 161, 148, 49, 1259, "Class 168");
    public static final DataTemplate C168_4 = addTemplateR("C168_4", 161, 197, 66, 1259, "Class 168");
    public static final DataTemplate C170_1_2 = addTemplateR("C170/1_2", 161, 101, 34, 629, "Class 170/1");
    public static final DataTemplate C170_1_3 = addTemplateR("C170/1_3", 161, 148, 49, 944, "Class 170/1");
    public static final DataTemplate C170_2_2 = addTemplateR("C170/2_2", 161, 99, 33, 629, "Class 170/2");
    public static final DataTemplate C170_2_3 = addTemplateR("C170/2_3", 161, 148, 49, 944, "Class 170/2");
    public static final DataTemplate C170_3_3 = addTemplateR("C170/3_3", 161, 148, 49, 629, "Class 170/3");
    public static final DataTemplate C170_4_3 = addTemplateR("C170/4_3", 161, 148, 49, 944, "Class 170/4");
    public static final DataTemplate C170_5_2 = addTemplateR("C170/5_2", 161, 99, 33, 944, "Class 170/5");
    public static final DataTemplate C170_6_3 = addTemplateR("C170/6_3", 161, 148, 49, 944, "Class 170/6");
    public static final DataTemplate C171_7_2 = addTemplateR("C171/7_2", 161, 108, 36, 629, "Class 171/7");
    public static final DataTemplate C171_8_4 = addTemplateR("C171/8_4", 161, 197, 66, 944, "Class 171/8");
    public static final DataTemplate C172_0_2 = addTemplateR("C172/0_2", 161, 92, 31, 722, "Class 172/0");
    public static final DataTemplate C172_1_2 = addTemplateR("C172/1_2", 161, 92, 31, 722, "Class 172/1");
    public static final DataTemplate C172_2_2 = addTemplateR("C172/2_2", 161, 94, 31, 722, "Class 172/2");
    public static final DataTemplate C172_3_3 = addTemplateR("C172/3_3", 161, 134, 45, 722, "Class 172/3");
    public static final DataTemplate C175_0_2 = addTemplateR("C175/0_2", 161, 110, 37, 671, "Class 175/0");
    public static final DataTemplate C175_1_3 = addTemplateR("C175/1_3", 161, 165, 55, 1007, "Class 175/1");
    public static final DataTemplate C180_5 = addTemplateR("C180_5", 201, 280, 70, 2796, "Class 180 *Zephyr*");
    public static final DataTemplate C185_3 = addTemplateR("C185_3", 161, 185, 92, 1678, "Class 185 *Pennine*");
    public static final DataTemplate C220_4 = addTemplateR("C220_4", 201, 228, 76, 2237, "Class 220 *Voyager*");
    public static final DataTemplate C221_4 = addTemplateR("C221_4", 201, 255, 85, 2237, "Class 221 *Super Voyager*");
    public static final DataTemplate C221_5 = addTemplateR("C221_5", 201, 314, 105, 2796, "Class 221 *Super Voyager*");
    public static final DataTemplate C222_0_5 = addTemplateR("C222/0_5", 201, 274, 91, 2088, "Class 222/0 *Meridian*");
    public static final DataTemplate C222_0_7 = addTemplateR("C222/0_7", 201, 384, 128, 3915, "Class 222/0 *Meridian*");
    public static final DataTemplate C222_0_4 = addTemplateR("C222/0_4", 201, 220, 73, 2237, "Class 222/0 *Meridian*");
    public static final DataTemplate C195_0_2 = addTemplateR("C195/0_2", 161, 25, 12, 780, "Class 195/0");
    public static final DataTemplate C195_1_3 = addTemplateR("C195/1_3", 161, 37, 18, 1170, "Class 195/1");
    public static final DataTemplate C196_0_2 = addTemplateR("C196/0_2", 161, 0, 0, 0, "Class 196/0");
    public static final DataTemplate C196_1_4 = addTemplateR("C196/1_4", 161, 0, 0, 0, "Class 196/1");
    public static final DataTemplate C197_0_2 = addTemplateR("C197/0_2", 161, 0, 0, 0, "Class 197/0");
    public static final DataTemplate C197_1_3 = addTemplateR("C197/1_3", 161, 0, 0, 0, "Class 197/1");
    public static final DataTemplate C230_2 = addTemplateR("C230_2", 161, 60, 30, 298, "Class 230");
    public static final DataTemplate C230_3 = addTemplateR("C230_3", 161, 91, 45, 298, "Class 230");
    public static final DataTemplate C231_4 = addTemplateR("C231_4", 161, 0, 0, 0, "Class 231");
    public static final DataTemplate C755_3_3 = addTemplateR("C755/3_3", 161, 0, 0, 0, "Class 755/3");
    public static final DataTemplate C755_4_4 = addTemplateR("C755/4_4", 161, 0, 0, 0, "Class 755/4");
    public static final DataTemplate C756_3_3 = addTemplateR("C756/3_3", 161, 0, 0, 0, "Class 756/3");
    public static final DataTemplate C756_4_4 = addTemplateR("C756/4_4", 161, 0, 0, 0, "Class 756/4");
    public static final DataTemplate C800_1_5 = addTemplateR("C800/1_5", 201, 272, 136, 701, "Class 800/1 *IET*");
    public static final DataTemplate C800_2_9 = addTemplateR("C800/2_9", 201, 492, 246, 701, "Class 800/2 *IET*");
    public static final DataTemplate C800_1_9 = addTemplateR("C800/1_9", 201, 492, 246, 559, "Class 800/1 *Azuma*");
    public static final DataTemplate C802_5 = addTemplateR("C802_5", 201, 273, 137, 701, "Class 802 *IET*");
    public static final DataTemplate C68_5 = addTemplateR("C68_5", 161, 241, 80, 2834, "Class 68 +MK5+DVT *Nova*");
    public static final DataTemplate C67_4 = addTemplateR("C67_4", 161, 148, 37, 1864, "Class 67 +MK3+DVT");
    public static final DataTemplate C67_6 = addTemplateR("C67_6", 161, 222, 55, 1864, "Class 67 +MK3+DVT");
    public static final DataTemplate C43_4 = addTemplateR("C43_4", 201, 157, 35, 984, "Class 43 HST *Castle*");
    public static final DataTemplate C43_6 = addTemplateR("C43_6", 201, 222, 35, 984, "Class 43 HST (2+6, B)");
    public static final DataTemplate C43_7 = addTemplateR("C43_7", 201, 259, 35, 984, "Class 43 HST (2+7)");
    public static final DataTemplate C43_8 = addTemplateR("C43_8", 201, 296, 35, 984, "Class 43 HST (2+8)");
    public static final DataTemplate C43_9 = addTemplateR("C43_9", 201, 333, 35, 984, "Class 43 HST (2+9)");

}
