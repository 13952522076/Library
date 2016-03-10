package com.sammyun.controller.api;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.controller.api.bean.RecipeDetailBean;
import com.sammyun.controller.api.bean.RecipeListBean;
import com.sammyun.controller.api.block.RecipeImageBlock;
import com.sammyun.controller.api.block.RecipeListBlock;
import com.sammyun.controller.api.block.RecipeSectionBlock;
import com.sammyun.controller.api.block.RecipeWeekDayBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.dict.DictSchool;
import com.sammyun.entity.recipe.RecipeImage;
import com.sammyun.entity.recipe.RecipeSection;
import com.sammyun.entity.recipe.RecipeWeekDay;
import com.sammyun.service.dict.DictSchoolService;
import com.sammyun.service.recipe.RecipeService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.ImUserUtil;

/**
 * Api - 学生食谱
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("recipe")
@Path("/recipe")
public class RecipeApi
{

    @Resource(name = "dictSchoolServiceImpl")
    private DictSchoolService dictSchoolService;

    @Resource(name = "recipeServiceImpl")
    private RecipeService recipeService;

    /**
     * 根据学校和时间段获取食谱信息
     * 
     * @param loginBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel list(RecipeListBean recipeListBean)
    {

        ListRestFulModel listRestFulModel = new ListRestFulModel();
        Long dictSchoolId = recipeListBean.getDictSchoolId();
        if (dictSchoolId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }
        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        if (dictSchool == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("未知学校不存在！");
            return listRestFulModel;
        }

        Pageable pageable;
        if (recipeListBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(recipeListBean.getPage().getPageNumber(), recipeListBean.getPage().getPageSize());

        }
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("year"));
        orders.add(Order.desc("month"));
        orders.add(Order.desc("week"));
        pageable.setOrders(orders);

        Page<com.sammyun.entity.recipe.Recipe> recipes = recipeService.findRecipes(dictSchool, pageable);

        if (recipes == null || recipes.getContent().size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("没有相关食谱信息！");
            return listRestFulModel;
        }
        List<RecipeListBlock> rows = new LinkedList<RecipeListBlock>();
        for (com.sammyun.entity.recipe.Recipe recipe : recipes.getContent())
        {
            RecipeListBlock recipeListBlock = new RecipeListBlock();
            recipeListBlock.setId(recipe.getId());
            recipeListBlock.setMonth(recipe.getMonth());
            recipeListBlock.setWeek(recipe.getWeek());
            recipeListBlock.setYear(recipe.getYear());
            recipeListBlock.setIsCurrent(recipe.getIsCurrent());
            if (recipe.getCreateDate() != null)
            {
                recipeListBlock.setCreateDate(DateUtil.date2String(recipe.getWeekEndDate(), 1));
            }
            rows.add(recipeListBlock);
        }
        PageModel page = new PageModel();
        page.setPageNumber(recipes.getPageNumber());
        page.setTotalPages(recipes.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取食谱信息成功");
        return listRestFulModel;
    }

    /**
     * 根据学校和时间段获取食谱信息
     * 
     * @param loginBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/detail")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel detail(RecipeDetailBean recipeDetailBean)
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        ImUserUtil imUserUtil = new ImUserUtil();
        Long dictSchoolId = recipeDetailBean.getDictSchoolId();
        Long year = recipeDetailBean.getYear();
        Long month = recipeDetailBean.getMonth();
        Long week = recipeDetailBean.getWeek();
        if (dictSchoolId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误！");
            return listRestFulModel;
        }

        DictSchool dictSchool = dictSchoolService.find(dictSchoolId);
        if (dictSchool == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("未知学校不存在！");
            return listRestFulModel;
        }

        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.asc("week"));
        orders.add(Order.asc("createDate"));
        List<com.sammyun.entity.recipe.Recipe> recipes = new LinkedList<com.sammyun.entity.recipe.Recipe>();
        if (year == null || month == null || week == null)
        {
            recipes = recipeService.findRecipes4Week(dictSchool, year, month, week, true, orders);
        }
        else
        {
            recipes = recipeService.findRecipes4Week(dictSchool, year, month, week, null, orders);
        }

        if (recipes == null || recipes.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("没有相关食谱信息！");
            return listRestFulModel;
        }

        List<RecipeListBlock> rows = new LinkedList<RecipeListBlock>();
        for (com.sammyun.entity.recipe.Recipe recipe : recipes)
        {
            RecipeListBlock recipeListBlock = new RecipeListBlock();
            recipeListBlock.setYear(recipe.getYear());
            recipeListBlock.setMonth(recipe.getMonth());
            recipeListBlock.setWeek(recipe.getWeek());
            if (recipe.getWeekEndDate() != null)
            {
                recipeListBlock.setWeekEndDate(DateUtil.date2String(recipe.getWeekEndDate(), 10));
            }
            if (recipe.getWeekStartDate() != null)
            {
                recipeListBlock.setWeekStartDate(DateUtil.date2String(recipe.getWeekStartDate(), 10));
            }
            List<RecipeWeekDayBlock> recipeWeekDays = new LinkedList<RecipeWeekDayBlock>();
            for (RecipeWeekDay recipeWeekDay : recipe.getRecipeWeekDays())
            {
                RecipeWeekDayBlock recipeWeekDayBlock = new RecipeWeekDayBlock();
                recipeWeekDayBlock.setId(recipeWeekDay.getId());
                recipeWeekDayBlock.setWeekDay(recipeWeekDay.getWeekDay());
                List<RecipeSectionBlock> recipeSections = new LinkedList<RecipeSectionBlock>();
                for (RecipeSection recipeSection : recipeWeekDay.getRecipeSections())
                {
                    RecipeSectionBlock recipeSectionBlock = new RecipeSectionBlock();
                    recipeSectionBlock.setId(recipeSection.getId());
                    recipeSectionBlock.setSectionName(recipeSection.getSectionName());
                    recipeSectionBlock.setDescription(recipeSection.getDescription());
                    List<RecipeImageBlock> recipeImages = new LinkedList<RecipeImageBlock>();
                    for (RecipeImage recipeDetail : recipeSection.getRecipeImages())
                    {
                        RecipeImageBlock recipeDetailBlock = new RecipeImageBlock();
                        recipeDetailBlock.setId(recipeDetail.getId());
                        recipeDetailBlock.setDishImage(imUserUtil.getDefaultImageUrl(recipeDetail.getDishImage()));
                        recipeImages.add(recipeDetailBlock);
                    }
                    recipeSectionBlock.setRecipeImages(recipeImages);
                    recipeSections.add(recipeSectionBlock);
                }
                recipeWeekDayBlock.setRecipeSections(recipeSections);
                recipeWeekDays.add(recipeWeekDayBlock);
            }
            recipeListBlock.setRecipeWeekDays(recipeWeekDays);
            rows.add(recipeListBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取食谱信息成功");
        return listRestFulModel;
    }
}
