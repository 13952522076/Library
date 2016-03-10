package com.sammyun.job;

import javax.annotation.Resource;

import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.sammyun.service.course.WeeklyPlanSectionService;
import com.sammyun.service.recipe.RecipeService;

@Component("isCurrentJob")
@Lazy(false)
public class IsCurrentJob
{
    @Resource(name = "weeklyPlanSectionServiceImpl")
    private WeeklyPlanSectionService weeklyPlanSectionService;
    
    @Resource(name = "recipeServiceImpl")
    private RecipeService recipeService;

    @Scheduled(cron = "${job.isCurrent.cron}")
    public void changeIsCurrent()
    {
        weeklyPlanSectionService.changeIsCurrent();
        recipeService.changeIsCurrent();
    }
}
