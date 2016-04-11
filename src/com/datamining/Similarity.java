package com.datamining;
import java.util.logging.Logger;  
import java.util.HashMap;  
import java.util.Iterator;  
import java.util.Map;

public class Similarity
{
    static Logger logger = Logger.getLogger(Similarity.class.getName());

    Map<String, Double> rating_map = new HashMap<String, Double>();

    /**
     * @param args
     */
    public static void main(String[] args)
    {
        Similarity similarity1 = new Similarity();
        similarity1.rating_map.put("1", 6d);
        similarity1.rating_map.put("2", 2d);
       // similarity1.rating_map.put("3", 0d);
        Similarity similarity2 = new Similarity();
        similarity2.rating_map.put("1", 6d);
        similarity2.rating_map.put("2", 2d);
        similarity2.rating_map.put("3", 6d);
        System.out.println("" + similarity1.getsimilarity_bydim(similarity2));
    }

    public double getsimilarity_bydim(Similarity u)
    {
        double sim = 0d;
        double common_items_len = 0;
        double this_sum = 0d;
        double u_sum = 0d;
        double this_sum_sq = 0d;
        double u_sum_sq = 0d;
        double p_sum = 0d;

        Iterator<String> rating_map_iterator = this.rating_map.keySet().iterator();
        while (rating_map_iterator.hasNext())
        {
            String rating_map_iterator_key = rating_map_iterator.next();
            Iterator<String> u_rating_map_iterator = u.rating_map.keySet().iterator();
            while (u_rating_map_iterator.hasNext())
            {
                String u_rating_map_iterator_key = u_rating_map_iterator.next();
                if (rating_map_iterator_key.equals(u_rating_map_iterator_key))
                {
                    double this_grade = this.rating_map.get(rating_map_iterator_key);
                    double u_grade = u.rating_map.get(u_rating_map_iterator_key);
                    // 评分求和
                    // 平方和
                    // 乘积和
                    this_sum += this_grade;
                    u_sum += u_grade;
                    this_sum_sq += Math.pow(this_grade, 2);
                    u_sum_sq += Math.pow(u_grade, 2);
                    p_sum += this_grade * u_grade;
                    common_items_len++;
                }
            }
        }
        // 如果等于零则无相同条目，返回sim=0即可
        if (common_items_len > 0)
        {
            logger.info("common_items_len:" + common_items_len);
            logger.info("p_sum:" + p_sum);
            logger.info("this_sum:" + this_sum);
            logger.info("u_sum:" + u_sum);
            double num = common_items_len * p_sum - this_sum * u_sum;
            double den = Math.sqrt((common_items_len * this_sum_sq - Math.pow(this_sum, 2))
                    * (common_items_len * u_sum_sq - Math.pow(u_sum, 2)));
            logger.info("" + num + ":" + den);
            sim = (den == 0) ? 1 : num / den;
        }

        // 如果等于零则无相同条目，返回sim=0即可
        return sim;
    }

}
