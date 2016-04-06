package com.datamining;

/**
 * 
 * imdb top 250 评分算法
 * imdb top 250用的是贝叶斯统计的算法得出的加权分(Weighted Rank-WR)，公式如下：
 * weighted rank (WR) = (v ÷ (v+m)) × R + (m ÷ (v+m)) × C
 *  其中：
 *  R = average for the movie (mean) = (Rating) （是用普通的方法计算出的平均分）
 *  v = number of votes for the movie = (votes) （投票人数，需要注意的是，只有经常投票者才会被计算在内，这个下面详细解释）
 *  m = minimum votes required to be listed in the top 250 (currently 1250) （排名需要的最小票数，只有三两个人投票的电影就算得满分也没用的）
 *  C = the mean vote across the whole report (currently 6.9) （目前所有电影的平均得分）
 *  
 * @author  maxu
 * @version  [版本号, 2016年4月6日]
 * @see  [相关类/方法]
 * @since  [产品/模块版本]
 */

public class WeightedRank
{
    /**
     * imdb Top 250 算法
     * <功能详细描述>
     * @param R 此项目目前的普通平均值
     * @param v 此项目目前投票人数（可优化）
     * @param m 排名需要的最小票数
     * @param C 目前所有电影的平均得分
     * @return 该项目的综合评分
     * @see [类、类#方法、类#成员]
     */
    public static Float imdbTop250(Float R,Float v,Float m,Float C){
        Float WR = 0F;
        WR = (v/(v+m))*R+(m/(v+m))*C;
        return WR;
    }
}
