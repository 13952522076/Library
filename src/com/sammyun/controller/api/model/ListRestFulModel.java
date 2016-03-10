package com.sammyun.controller.api.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;

import com.sammyun.controller.api.block.AnnouncementSummaryBlock;
import com.sammyun.controller.api.block.AttendaceClassInfoBlock;
import com.sammyun.controller.api.block.AttendaceListBlock;
import com.sammyun.controller.api.block.AttendanceAddBlock;
import com.sammyun.controller.api.block.AttendanceEquipmentCardBlock;
import com.sammyun.controller.api.block.AttendanceEquipmentListBlock;
import com.sammyun.controller.api.block.CampusviewImgAlbumsBlock;
import com.sammyun.controller.api.block.ClassAlbumImageContentBlock;
import com.sammyun.controller.api.block.ClassAlbumImageListBlock;
import com.sammyun.controller.api.block.CurriculumScheduleForSchoolBlock;
import com.sammyun.controller.api.block.CurriculumScheduleListBlock;
import com.sammyun.controller.api.block.FriendsListBlock;
import com.sammyun.controller.api.block.GrowthDiaryFileBlock;
import com.sammyun.controller.api.block.MessageListBlock;
import com.sammyun.controller.api.block.MessageMemberInfoBlock;
import com.sammyun.controller.api.block.NewsCategoryBlock;
import com.sammyun.controller.api.block.NewsSummaryBlock;
import com.sammyun.controller.api.block.ParentingCategoryBlock;
import com.sammyun.controller.api.block.PersonalProvinceBlock;
import com.sammyun.controller.api.block.PersonalTypeBlock;
import com.sammyun.controller.api.block.PosterListBlock;
import com.sammyun.controller.api.block.QualityCourseListBlock;
import com.sammyun.controller.api.block.RecipeListBlock;
import com.sammyun.controller.api.block.TeacherAskLeaveTypeBlock;
import com.sammyun.controller.api.block.TeacherAttendanceListBlock;
import com.sammyun.controller.api.block.WeeklyPlanSectionListBlock;
import com.sammyun.controller.api.block.app.AppBlock;
import com.sammyun.controller.api.block.app.AppCategoryBlock;
import com.sammyun.controller.api.block.app.AppPosterBlock;
import com.sammyun.controller.api.block.app.AppScreenshotBlock;
import com.sammyun.entity.app.AppClientVersion;

@XmlRootElement(name = "attendanceRestFulModel")
@XmlSeeAlso({AttendaceClassInfoBlock.class, AttendaceListBlock.class, RecipeListBlock.class,
        CurriculumScheduleForSchoolBlock.class, CurriculumScheduleListBlock.class, PersonalProvinceBlock.class,
        PersonalTypeBlock.class, NewsCategoryBlock.class, NewsSummaryBlock.class, AnnouncementSummaryBlock.class,
        CampusviewImgAlbumsBlock.class, WeeklyPlanSectionListBlock.class, PosterListBlock.class,
        MessageListBlock.class, MessageMemberInfoBlock.class, AttendanceEquipmentListBlock.class,
        FriendsListBlock.class, QualityCourseListBlock.class, ClassAlbumImageListBlock.class,
        ClassAlbumImageContentBlock.class, ParentingCategoryBlock.class, TeacherAttendanceListBlock.class,
        GrowthDiaryFileBlock.class, AttendanceAddBlock.class, AttendanceEquipmentCardBlock.class,
        TeacherAskLeaveTypeBlock.class, AppBlock.class, AppScreenshotBlock.class, AppCategoryBlock.class, AppPosterBlock.class,
        AppClientVersion.class
})
public class ListRestFulModel
{
    /** 错误编码 */
    private Integer resultCode;

    /** 错误信息 */
    private String resultMessage;

    private List<?> rows;

    /** 分页信息 */
    private PageModel page;

    @XmlElement
    public Integer getResultCode()
    {
        return resultCode;
    }

    public void setResultCode(Integer resultCode)
    {
        this.resultCode = resultCode;
    }

    @XmlElement
    public String getResultMessage()
    {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage)
    {
        this.resultMessage = resultMessage;
    }

    @XmlElement
    public List<?> getRows()
    {
        return rows;
    }

    public void setRows(List<?> rows)
    {
        this.rows = rows;
    }

    @XmlElement
    public PageModel getPage()
    {
        return page;
    }

    public void setPage(PageModel page)
    {
        this.page = page;
    }

}
