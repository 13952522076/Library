package com.sammyun.controller.api;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.spring.scope.RequestContextFilter;

import com.sammyun.controller.api.app.AppApi;
import com.sammyun.controller.api.app.AppCategoryApi;
import com.sammyun.controller.api.app.AppClientVersionApi;
import com.sammyun.controller.api.app.AppPosterApi;
import com.sammyun.controller.api.app.AppReviewApi;
import com.sammyun.controller.api.app.AppUserApi;

/**
 * Config - 接口服务注册
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class ApplicationResourceConfig extends ResourceConfig
{
    public ApplicationResourceConfig()
    {

        // 加载resources
        register(AnnouncementApi.class);
        register(AttendanceApi.class);
        register(AttendanceEquipmentApi.class);
        register(CampusviewImgApi.class);
        register(CurriculumScheduleApi.class);
        register(LoginApi.class);
        register(MessageApi.class);
        register(NewsApi.class);
        register(PersonalApi.class);
        register(PosterApi.class);
        register(ProfileApi.class);
        register(RecipeApi.class);
        register(SystemSuggestApi.class);
        register(WeeklyPlanSectionApi.class);
        register(FriendsApi.class);
        register(QualityCourseApi.class);
        register(ClassAlbumImageApi.class);
        register(ParentingApi.class);
        register(TeacherAttendanceApi.class);
        register(GrowthDiaryApi.class);
        register(TeacherAskLeaveApi.class);
        register(AppApi.class);
        register(AppCategoryApi.class);
        register(AppClientVersionApi.class);
        register(AppPosterApi.class);
        register(AppReviewApi.class);
        register(AppUserApi.class);
        register(AdApi.class);
        
        register(RequestContextFilter.class);
        register(JacksonFeature.class);
        register(MultiPartFeature.class);

    }
}
