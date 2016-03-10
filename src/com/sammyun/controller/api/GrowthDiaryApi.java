package com.sammyun.controller.api;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;

import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataBodyPart;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import com.sammyun.FileInfo.FileType;
import com.sammyun.Order;
import com.sammyun.Page;
import com.sammyun.Pageable;
import com.sammyun.Setting;
import com.sammyun.controller.api.bean.GrowthDiaryDetailBean;
import com.sammyun.controller.api.bean.GrowthDiaryListBean;
import com.sammyun.controller.api.bean.GrowthDiaryPlayBean;
import com.sammyun.controller.api.bean.GrowthDiarySaveBean;
import com.sammyun.controller.api.bean.GrowthDiaryShareBean;
import com.sammyun.controller.api.block.DiaryTagListBlock;
import com.sammyun.controller.api.block.GrowthDiaryDetailBlock;
import com.sammyun.controller.api.block.GrowthDiaryFileBlock;
import com.sammyun.controller.api.block.GrowthDiaryListBlock;
import com.sammyun.controller.api.model.ListRestFulModel;
import com.sammyun.controller.api.model.MobileRestFulModel;
import com.sammyun.controller.api.model.PageModel;
import com.sammyun.entity.Member;
import com.sammyun.entity.Member.MemberType;
import com.sammyun.entity.gd.DiaryAgree;
import com.sammyun.entity.gd.DiaryPicture;
import com.sammyun.entity.gd.DiaryTag;
import com.sammyun.entity.gd.DiaryTranspond;
import com.sammyun.entity.gd.DiaryTranspond.Shared;
import com.sammyun.entity.gd.GrowthDiary.DiaryType;
import com.sammyun.plugin.StoragePlugin;
import com.sammyun.service.MemberService;
import com.sammyun.service.dict.ClassTeacherMapService;
import com.sammyun.service.dict.PatriarchStudentMapService;
import com.sammyun.service.gd.DiaryAgreeService;
import com.sammyun.service.gd.DiaryPictureService;
import com.sammyun.service.gd.DiaryTagService;
import com.sammyun.service.gd.DiaryTranspondService;
import com.sammyun.service.gd.GrowthDiaryService;
import com.sammyun.util.DateUtil;
import com.sammyun.util.FreemarkerUtils;
import com.sammyun.util.ImUserUtil;
import com.sammyun.util.SettingUtils;
import com.sammyun.util.SpringUtils;
import com.sammyun.util.SystemDictUtils;

/**
 * api - 成长日记
 * 
 * @author xutianlong
 * @version [版本号, Aug 12, 2015]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component("growthDiary")
@Path("/growthDiary")
public class GrowthDiaryApi
{
    // /** 目标文件类型 */
    // private static final String DEST_CONTENT_TYPE = "image/jpeg";
    /** 日志 */
    private static final Logger logger = LoggerFactory.getLogger(GrowthDiaryApi.class);

    @Resource(name = "taskExecutor")
    private TaskExecutor taskExecutor;

    @Resource(name = "growthDiaryServiceImpl")
    private GrowthDiaryService growthDiaryService;

    @Resource(name = "memberServiceImpl")
    private MemberService memberService;

    @Resource(name = "classTeacherMapServiceImpl")
    private ClassTeacherMapService classTeacherMapService;

    @Resource(name = "patriarchStudentMapServiceImpl")
    private PatriarchStudentMapService patriarchStudentMapService;

    @Resource
    private List<StoragePlugin> storagePlugins;

    // private ServletFileUpload upload;

    @Resource(name = "diaryAgreeServiceImpl")
    private DiaryAgreeService diaryAgreeService;

    @Resource(name = "diaryPictureServiceImpl")
    private DiaryPictureService diaryPictureService;

    @Resource(name = "diaryTranspondServiceImpl")
    private DiaryTranspondService diaryTranspondService;

    @Resource(name = "diaryTagServiceImpl")
    private DiaryTagService diaryTagService;

    @Resource(name = "ehCacheManager")
    private CacheManager cacheManager;

    /**
     * 上传语音，图片
     * 
     * @throws IOException
     */
    // @POST
    // @Path("/v1/upload")
    // @Produces("application/json;charset=UTF-8")
    // @Consumes(MediaType.MULTIPART_FORM_DATA)
    // @SuppressWarnings({"unchecked", "null"})
    // public ListRestFulModel upload(@Context HttpServletRequest request)
    // throws ServletException, IOException,
    // FileUploadException
    // {
    // FileItemFactory factory = new DiskFileItemFactory();
    // this.upload = new ServletFileUpload(factory);
    // List<FileItem> items = this.upload.parseRequest(request);
    //
    // ListRestFulModel listRestFulModel = new ListRestFulModel();
    // List<GrowthDiaryFileBlock> rows = new
    // LinkedList<GrowthDiaryFileBlock>();
    // Setting setting = SettingUtils.get();
    //
    // if (items == null || items.size() == 0)
    // {
    // listRestFulModel.setResultCode(1);
    // listRestFulModel.setResultMessage(SpringUtils.getMessage("参数错误！"));
    // return listRestFulModel;
    // }
    // for (FileItem fileItem : items)
    // {
    // String fileName = fileItem.getName();
    // InputStream stream = fileItem.getInputStream();
    // String fileType = fileItem.getContentType();
    // if (fileType != null)
    // { // for test,delete after
    // continue;
    // }
    // // 处理fileType
    // try
    // {
    // String uploadPath = "";
    // if (fileType.equals(FileType.flash.toString()))
    // {
    // uploadPath = setting.getFlashUploadPath();
    // }
    // else if (fileType.equals(FileType.media.toString()))
    // {
    // uploadPath = setting.getMediaUploadPath();
    // }
    // else if (fileType.equals(FileType.file.toString()))
    // {
    // uploadPath = setting.getFileUploadPath();
    // }
    // else
    // {
    // uploadPath = setting.getImageUploadPath();
    // }
    //
    // Map<String, Object> model = new HashMap<String, Object>();
    // model.put("uuid", UUID.randomUUID().toString());
    // String path = FreemarkerUtils.process(uploadPath, model);
    // String sourcePath = path + UUID.randomUUID() + "." +
    // FilenameUtils.getExtension(fileName);
    // Collections.sort(storagePlugins);
    // for (StoragePlugin storagePlugin : storagePlugins)
    // {
    // if (storagePlugin.getIsEnabled())
    // {
    // File tempFile = new File(System.getProperty("java.io.tmpdir") +
    // "/upload_" + UUID.randomUUID()
    // + ".tmp");
    // if (!tempFile.getParentFile().exists())
    // {
    // tempFile.getParentFile().mkdirs();
    // }
    // OutputStream os = new FileOutputStream(tempFile);
    // int bytesRead = 0;
    // byte[] buffer = new byte[8192];
    // while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
    // {
    // os.write(buffer, 0, bytesRead);
    // }
    // os.close();
    // stream.close();
    // // 上传为图片
    // if (fileType.equals("image"))
    // {
    // addTask(sourcePath, tempFile, fileType);
    // }
    // if (fileType.equals("image"))
    // {
    //
    // }
    // addTask(sourcePath, tempFile, fileType);
    // String url = storagePlugin.getUrl(sourcePath);
    // //
    // // AttendanceEquipmentUploadBlock
    // // attendanceEquipmentUploadBlock = new
    // // AttendanceEquipmentUploadBlock();
    // // attendanceEquipmentUploadBlock.setFileName(fileName);
    // // attendanceEquipmentUploadBlock.setUrl(url);
    // GrowthDiaryFileBlock growthDiaryFileBlock = new GrowthDiaryFileBlock();
    // growthDiaryFileBlock.setFileType(fileType);// 需要判断
    // growthDiaryFileBlock.setFileUrl(url);
    // rows.add(growthDiaryFileBlock);
    // }
    // }
    // }
    // catch (Exception e)
    // {
    // // TODO: handle exception
    // }
    // }
    // listRestFulModel.setRows(rows);
    // listRestFulModel.setResultCode(0);
    // listRestFulModel.setResultMessage("上传成功！");
    // return listRestFulModel;
    // }

    @POST
    @Path("/v1/upload")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    public ListRestFulModel upload(FormDataMultiPart form) throws ServletException, IOException, FileUploadException
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<GrowthDiaryFileBlock> rows = new LinkedList<GrowthDiaryFileBlock>();
        Setting setting = SettingUtils.get();
        List<BodyPart> formLists = form.getBodyParts();
        if (formLists == null || formLists.size() == 0)
        {
            listRestFulModel.setRows(rows);
            listRestFulModel.setResultCode(0);
            listRestFulModel.setResultMessage("无文件");
            return listRestFulModel;
        }
        for (BodyPart formList : formLists)
        {
            InputStream stream = ((FormDataBodyPart) formList).getValueAs(InputStream.class);
            InputStream stream2 = ((FormDataBodyPart) formList).getValueAs(InputStream.class);
            FormDataContentDisposition detail = ((FormDataBodyPart) formList).getFormDataContentDisposition();
            MediaType type = formList.getMediaType();
            String fileType = type.getType();
            String fileName = detail.getFileName();
            // 处理fileType
            try
            {
                String uploadPath = "";
                if (fileType.equals(FileType.flash.toString()))
                {
                    uploadPath = setting.getFlashUploadPath();
                }
                else if (fileType.equals(FileType.media.toString()))
                {
                    uploadPath = setting.getMediaUploadPath();
                }
                else if (fileType.equals("audio"))
                {
                    uploadPath = setting.getMediaUploadPath();
                }
                else if (fileType.equals(FileType.file.toString()))
                {
                    uploadPath = setting.getFileUploadPath();
                }
                else
                {
                    uploadPath = setting.getImageUploadPath();
                }

                Map<String, Object> model = new HashMap<String, Object>();
                model.put("uuid", UUID.randomUUID().toString());
                String path = FreemarkerUtils.process(uploadPath, model);
                String sourcePath = path + UUID.randomUUID() + "." + FilenameUtils.getExtension(fileName);
                Collections.sort(storagePlugins);
                for (StoragePlugin storagePlugin : storagePlugins)
                {
                    if (storagePlugin.getIsEnabled())
                    {
                        File tempFile = new File(System.getProperty("java.io.tmpdir") + "/upload_" + UUID.randomUUID()
                                + ".tmp");
                        if (!tempFile.getParentFile().exists())
                        {
                            tempFile.getParentFile().mkdirs();
                        }
                        OutputStream os = new FileOutputStream(tempFile);
                        int bytesRead = 0;
                        byte[] buffer = new byte[8192];
                        while ((bytesRead = stream.read(buffer, 0, 8192)) != -1)
                        {
                            os.write(buffer, 0, bytesRead);
                        }
                        os.close();

                        File tempFile2 = new File(System.getProperty("java.io.tmpdir") + "/upload_" + UUID.randomUUID()
                                + ".tmp");
                        if (!tempFile2.getParentFile().exists())
                        {
                            tempFile2.getParentFile().mkdirs();
                        }
                        OutputStream os2 = new FileOutputStream(tempFile2);
                        int bytesRead2 = 0;
                        byte[] buffer2 = new byte[8192];
                        while ((bytesRead2 = stream2.read(buffer2, 0, 8192)) != -1)
                        {
                            os2.write(buffer2, 0, bytesRead2);
                        }

                        os2.close();
                        stream.close();
                        stream2.close();
                        // 上传为图片
                        if (fileType.equals("image"))
                        {
                            // System.out.println(sourcePath.substring(0,sourcePath.indexOf("."))+".aud");
                            addTask(sourcePath, tempFile, fileType);
                            String url = storagePlugin.getUrl(sourcePath);
                            GrowthDiaryFileBlock growthDiaryFileBlock = new GrowthDiaryFileBlock();
                            growthDiaryFileBlock.setFileType(fileType);// 需要判断
                            growthDiaryFileBlock.setFileUrl(url);
                            rows.add(growthDiaryFileBlock);
                        }
                        if (fileType.equals("audio"))
                        {
                            // IOS
                            sourcePath = sourcePath.substring(0, sourcePath.indexOf(".")) + ".aud";
                            addTask(sourcePath, tempFile, fileType);
                            String url = storagePlugin.getUrl(sourcePath);
                            GrowthDiaryFileBlock growthDiaryFileBlock = new GrowthDiaryFileBlock();
                            growthDiaryFileBlock.setFileType("audUrl");// 需要判断
                            growthDiaryFileBlock.setFileUrl(url);
                            rows.add(growthDiaryFileBlock);
                            // android
                            sourcePath = sourcePath.substring(0, sourcePath.indexOf(".")) + ".amr";
                            addTask(sourcePath, tempFile2, fileType);
                            String url2 = storagePlugin.getUrl(sourcePath);
                            GrowthDiaryFileBlock growthDiaryFileBlock2 = new GrowthDiaryFileBlock();
                            growthDiaryFileBlock2.setFileType("amrUrl");// 需要判断
                            growthDiaryFileBlock2.setFileUrl(url2);
                            rows.add(growthDiaryFileBlock2);
                        }

                    }
                }
            }
            catch (Exception e)
            {
                logger.error("file upload fail", e);
            }
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("上传成功！");
        return listRestFulModel;
    }

    /**
     * 保存成长记的描述以及地点
     * 
     * @param dictSchoolId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/save")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public MobileRestFulModel save(GrowthDiarySaveBean growthDiarySaveBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (growthDiarySaveBean == null || growthDiarySaveBean.getMemberId() == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("用户参数错误！");
            return mobileRestFulModel;
        }
        Member member = memberService.find(growthDiarySaveBean.getMemberId());
        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知用户错误！");
            return mobileRestFulModel;
        }
        com.sammyun.entity.gd.GrowthDiary growthDiary = new com.sammyun.entity.gd.GrowthDiary();
        if (growthDiarySaveBean.getAmrUrl() == null && growthDiarySaveBean.getAudUrl() == null)
        {
            if (growthDiarySaveBean.getImages() == null || growthDiarySaveBean.getImages().size() == 0)
            {
                growthDiary.setDiaryType(DiaryType.words);
            }
            else
            {
                growthDiary.setDiaryType(DiaryType.photo);
            }
        }
        else
        {
            growthDiary.setDiaryType(DiaryType.speech);
        }
        growthDiary.setDiaryMsg(growthDiarySaveBean.getDiaryMsg());
        growthDiary.setAddress(growthDiarySaveBean.getAddress());
        growthDiary.setMember(member);
        growthDiary.setCollectCount(0);
        growthDiary.setCommentCount(0);
        growthDiary.setTranspondCount(0);
        growthDiary.setAgreeCount(0);
        growthDiary.setReadCount(0);
        growthDiary.setAmrUrl(growthDiarySaveBean.getAmrUrl());
        growthDiary.setAudUrl(growthDiarySaveBean.getAudUrl());
        List<Long> diaryTagIds = growthDiarySaveBean.getDiaryTagIds();
        if (diaryTagIds != null && diaryTagIds.size() > 0)
        {
            Set<DiaryTag> diaryTags = new HashSet<DiaryTag>();
            for (Long diaryTagId : diaryTagIds)
            {
                DiaryTag diaryTag = diaryTagService.find(diaryTagId);
                if (diaryTag != null)
                {
                    diaryTags.add(diaryTag);
                }
            }
            growthDiary.setDiaryTags(diaryTags);
        }

        growthDiaryService.save(growthDiary);
        List<String> images = growthDiarySaveBean.getImages();
        if (images != null && images.size() > 0)
        {
            for (String image : images)
            {
                DiaryPicture diaryPicture = new DiaryPicture();
                diaryPicture.setGrowthDiary(growthDiary);
                diaryPicture.setPictureTime(new Date());
                diaryPicture.setPictureUrl(image);
                diaryPictureService.save(diaryPicture);
            }
        }
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("发布成功！");
        mobileRestFulModel.setRows(growthDiary.getId());
        return mobileRestFulModel;
    }

    /**
     * 添加图片处理任务
     * 
     * @param sourcePath 原图片上传路径
     * @param tempFile 原临时文件
     * @param contentType 原文件类型
     */
    private void addTask(final String sourcePath, final File tempFile, final String contentType)
    {
        try
        {
            taskExecutor.execute(new Runnable()
            {
                public void run()
                {
                    Collections.sort(storagePlugins);
                    for (StoragePlugin storagePlugin : storagePlugins)
                    {
                        if (storagePlugin.getIsEnabled())
                        {
                            try
                            {
                                storagePlugin.upload(sourcePath, tempFile, contentType);
                            }
                            finally
                            {
                                FileUtils.deleteQuietly(tempFile);
                            }
                            break;
                        }
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @POST
    @Path("/v1/list")
    @Produces("application/json;charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON)
    public ListRestFulModel list(GrowthDiaryListBean growthDiaryListBean)
    {
        ImUserUtil imUserUtil = new ImUserUtil();
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<GrowthDiaryListBlock> rows = new LinkedList<GrowthDiaryListBlock>();
        if (growthDiaryListBean == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("参数错误");
            return listRestFulModel;
        }
        Long memberId = growthDiaryListBean.getMemberId();
        if (memberId == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("用户参数错误");
            return listRestFulModel;
        }
        Member member = memberService.find(memberId);
        if (member == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("用户不存在");
            return listRestFulModel;
        }
        MemberType memberType = member.getMemberType();
        if (memberType == null)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("用户类型未知");
            return listRestFulModel;
        }
        List<Member> friends = new ArrayList<Member>();
        if (memberType == MemberType.patriarch)
        {
            friends = patriarchStudentMapService.findDiaryFriends(member);
        }
        if (memberType == MemberType.teacher)
        {
            friends = classTeacherMapService.findDiaryMembers(member);
        }
        if (friends == null || friends.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("搜索好友失败");
            return listRestFulModel;
        }
        Pageable pageable;
        if (growthDiaryListBean.getPage() == null)
        {
            pageable = new Pageable();
        }
        else
        {
            pageable = new Pageable(growthDiaryListBean.getPage().getPageNumber(),
                    growthDiaryListBean.getPage().getPageSize());
        }
        List<Order> orders = new LinkedList<Order>();
        orders.add(Order.desc("createDate"));
        pageable.setOrders(orders);
        Page<com.sammyun.entity.gd.GrowthDiary> growthDiaries = growthDiaryService.findPage(friends, pageable);
        if (growthDiaries == null || growthDiaries.getPageSize() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("无成长记列表");
            return listRestFulModel;
        }
        // 定义获取关于成长记的缓存
        Ehcache cache = cacheManager.getEhcache(com.sammyun.entity.gd.GrowthDiary.HITS_CACHE_NAME);
        // 定义获取关于成长记阅读的缓存
        Ehcache playCache = cacheManager.getEhcache(com.sammyun.entity.gd.GrowthDiary.PLAY_CACHE_NAME);
        for (com.sammyun.entity.gd.GrowthDiary growthDiary : growthDiaries.getContent())
        {
            GrowthDiaryListBlock growthDiaryListBlock = new GrowthDiaryListBlock();
            // growthDiaryListBlock.setCreateDate(growthDiary.getCreateDate().toString());
            // if (growthDiaryListBean.getMemberId() ==
            // growthDiary.getMember().getId())
            if (growthDiaryListBean.getMemberId().equals(growthDiary.getMember().getId()))
            {
                growthDiaryListBlock.setDeleteable(true);
            }
            else
            {
                growthDiaryListBlock.setDeleteable(false);
            }
            growthDiaryListBlock.setTransponded(diaryTranspondService.isTransponded(member, growthDiary));
            growthDiaryListBlock.setCreateDate(DateUtil.date2String(growthDiary.getCreateDate(), 88));
            growthDiaryListBlock.setDiaryId(growthDiary.getId());
            growthDiaryListBlock.setIconPhoto(imUserUtil.getDefaultImageUrl(growthDiary.getMember().getIconPhoto()));
            List<String> pictures = new ArrayList<String>();
            if (growthDiary.getDiaryPictures() != null && growthDiary.getDiaryPictures().size() > 0)
            {
                Set<DiaryPicture> diaryPictureSet = growthDiary.getDiaryPictures();
                List<DiaryPicture> diaryPictureList = new ArrayList<DiaryPicture>(diaryPictureSet);

                /* 将list有序排列 */
                Collections.sort(diaryPictureList, new Comparator<DiaryPicture>()
                {
                    public int compare(DiaryPicture arg0, DiaryPicture arg1)
                    {
                        return arg0.getId().compareTo(arg1.getId()); // 按照id排列
                    }
                });

                for (DiaryPicture diaryPicture : diaryPictureList)
                {
                    pictures.add(diaryPicture.getPictureUrl());
                }
            }
            growthDiaryListBlock.setImages(pictures);
            growthDiaryListBlock.setMemberId(growthDiary.getMember().getId());
            growthDiaryListBlock.setRealName(growthDiary.getMember().getRealName());
            growthDiaryListBlock.setTranspondCount(growthDiary.getTranspondCount());
            growthDiaryListBlock.setAudUrl(growthDiary.getAudUrl());
            growthDiaryListBlock.setAmrUrl(growthDiary.getAmrUrl());
            // growthDiaryListBlock.setReadCount(growthDiary.getReadCount());
            // 修改获得阅读量的获取，通过ehcache判断，存在从ehcache获取，不存在从表获取
            Element element = cache.get(growthDiary.getId() + "_count");
            if (element != null)
            {
                growthDiaryListBlock.setReadCount((Integer) element.getValue());
            }
            else
            {
                growthDiaryListBlock.setReadCount(growthDiary.getReadCount());
            }
            // 同样以缓存的方式获取播放次数
            Element playElement = playCache.get(growthDiary.getId() + "_playCount");
            if (playElement != null)
            {
                growthDiaryListBlock.setPlayCount((Integer) playElement.getValue());
            }
            else
            {
                growthDiaryListBlock.setPlayCount(growthDiary.getReadCount());
            }
            // 敏感词过滤
            growthDiaryListBlock.setDiaryMsg(SystemDictUtils.changeSensitiveWords(growthDiary.getDiaryMsg()));
            // 这里是获取当前人是否为此条纪录点赞
            growthDiaryListBlock.setIsAgree(diaryAgreeService.findIsagree(member, growthDiary));
            rows.add(growthDiaryListBlock);
        }
        if (rows.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("无成长记列表");
            return listRestFulModel;
        }
        PageModel page = new PageModel();
        page.setPageNumber(growthDiaries.getPageNumber());
        page.setTotalPages(growthDiaries.getTotalPages());
        listRestFulModel.setPage(page);
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage(SpringUtils.getMessage("获取成长记列表成功！"));
        return listRestFulModel;
    }

    /**
     * 点赞，取消点赞接口
     * 
     * @param memberId
     * @param growthDiaryId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/agree/{memberId}/{growthDiaryId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel agree(@PathParam("memberId") Long memberId, @PathParam("growthDiaryId") Long growthDiaryId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        Member member = memberService.find(memberId);
        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("当前用户未知");
            return mobileRestFulModel;
        }
        com.sammyun.entity.gd.GrowthDiary growthDiary = growthDiaryService.find(growthDiaryId);
        if (growthDiary == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("成长记未知");
            return mobileRestFulModel;
        }

        List<DiaryAgree> diaryAgrees = diaryAgreeService.findByMemberAndDiary(member, growthDiary);
        if (diaryAgrees == null || diaryAgrees.size() == 0)
        {
            DiaryAgree newDiaryAgree = new DiaryAgree();
            newDiaryAgree.setAgreeTime(new Date());
            newDiaryAgree.setCreateDate(new Date());
            newDiaryAgree.setGrowthDiary(growthDiary);
            newDiaryAgree.setMember(member);
            newDiaryAgree.setModifyDate(new Date());
            diaryAgreeService.save(newDiaryAgree);
            growthDiary.setAgreeCount(growthDiary.getAgreeCount() + 1);
            growthDiaryService.update(growthDiary);
            mobileRestFulModel.setResultMessage("点赞操作成功");
        }
        else
        {
            for (DiaryAgree diaryAgree : diaryAgrees)
            {
                diaryAgreeService.delete(diaryAgree);
            }
            growthDiary.setAgreeCount(growthDiary.getAgreeCount() - 1);
            growthDiaryService.update(growthDiary);
            mobileRestFulModel.setResultMessage("取消点赞操作成功");
        }
        mobileRestFulModel.setResultCode(0);
        return mobileRestFulModel;
    }

    /**
     * 显示详情接口
     * 
     * @param growthDiaryId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/detail")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel detail(GrowthDiaryDetailBean growthDiaryDetailBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        GrowthDiaryDetailBlock growthDiaryDetailBlock = new GrowthDiaryDetailBlock();
        ImUserUtil imUserUtil = new ImUserUtil();
        if ((growthDiaryDetailBean == null) || (growthDiaryDetailBean.getGrowthDiaryId() == null)
                || (growthDiaryDetailBean.getMemberId() == null))
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误");
            return mobileRestFulModel;
        }
        Long growthDiaryId = growthDiaryDetailBean.getGrowthDiaryId();
        Long memberId = growthDiaryDetailBean.getMemberId();

        com.sammyun.entity.gd.GrowthDiary growthDiary = growthDiaryService.find(growthDiaryId);
        if (growthDiary == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知成长记");
            return mobileRestFulModel;
        }
        Member member = memberService.find(memberId);
        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知用户");
            return mobileRestFulModel;
        }
        Set<DiaryTag> diaryTags = growthDiary.getDiaryTags();
        List<String> diaryTagNames = new ArrayList<String>();
        if (diaryTags != null && diaryTags.size() > 0)
        {
            for (DiaryTag diaryTag : diaryTags)
            {
                diaryTagNames.add(diaryTag.getName());
            }
        }
        if (growthDiaryDetailBean.getMemberId().equals(growthDiary.getMember().getId()))
        {
            growthDiaryDetailBlock.setDeleteable(true);
        }
        else
        {
            growthDiaryDetailBlock.setDeleteable(false);
        }
        growthDiaryDetailBlock.setTransponded(diaryTranspondService.isTransponded(member, growthDiary));
        growthDiaryDetailBlock.setIsAgree(diaryAgreeService.findIsagree(member, growthDiary));
        growthDiaryDetailBlock.setImages(diaryPictureService.findByGrowthDiary(growthDiary));
        growthDiaryDetailBlock.setAmrUrl(growthDiary.getAmrUrl());
        growthDiaryDetailBlock.setAudUrl(growthDiary.getAudUrl());
        growthDiaryDetailBlock.setAddress(growthDiary.getAddress());
        growthDiaryDetailBlock.setIconPhoto(imUserUtil.getDefaultImageUrl(growthDiary.getMember().getIconPhoto()));
        growthDiaryDetailBlock.setRealName(growthDiary.getMember().getRealName());
        growthDiaryDetailBlock.setTranspondCount(growthDiary.getTranspondCount());
        growthDiaryDetailBlock.setCreateDate(DateUtil.date2String(growthDiary.getCreateDate(), 1));
        growthDiaryDetailBlock.setAgreeCount(growthDiary.getAgreeCount());
        growthDiaryDetailBlock.setDiaryMsg(growthDiary.getDiaryMsg());
        growthDiaryDetailBlock.setDiaryTagNames(diaryTagNames);
        List<String> agreeIconPhotoes = new ArrayList<String>();
        List<Member> agreeMembers = diaryAgreeService.findMemberByGrowthDiary(growthDiary);
        if (agreeMembers != null && agreeMembers.size() > 0)
        {
            for (Member agreeMember : agreeMembers)
            {
                // TODO
                agreeIconPhotoes.add(imUserUtil.getDefaultImageUrl(agreeMember.getIconPhoto()));
            }
        }
        // 设置阅读量加一(本人阅读不加)
        // growthDiary.setReadCount(growthDiary.getReadCount() + 1);
        // growthDiaryService.update(growthDiary);
        growthDiaryService.viewCount(growthDiaryId, memberId);// ehcache先缓存，再设置

        growthDiaryDetailBlock.setAgreeIconPhotoes(agreeIconPhotoes);
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("详情显示");
        mobileRestFulModel.setRows(growthDiaryDetailBlock);
        return mobileRestFulModel;
    }

    /**
     * 播放次数增加接口
     * 
     * @param growthDiaryId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/playCount")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel readCount(GrowthDiaryPlayBean growthDiaryPlayBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if (growthDiaryPlayBean == null || growthDiaryPlayBean.getGrowthDiaryId() == null
                || growthDiaryPlayBean.getMemberId() == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误");
            return mobileRestFulModel;
        }
        com.sammyun.entity.gd.GrowthDiary growthDiary = growthDiaryService.find(growthDiaryPlayBean.getGrowthDiaryId());
        if (growthDiary == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知成长记");
            return mobileRestFulModel;
        }

        growthDiaryService.playCount(growthDiaryPlayBean.getGrowthDiaryId(), growthDiaryPlayBean.getMemberId());// ehcache先缓存，再设置
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("播放次数加一");
        return mobileRestFulModel;
    }

    /**
     * 成长记分享接口 <功能详细描述>
     * 
     * @param growthDiaryShareBean
     * @return
     * @see [类、类#方法、类#成员]
     */
    @POST
    @Path("/v1/share")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel share(GrowthDiaryShareBean growthDiaryShareBean)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        if ((growthDiaryShareBean == null) || (growthDiaryShareBean.getGrowthDiaryId() == null)
                || (growthDiaryShareBean.getMemberId() == null) || (growthDiaryShareBean.getShared() == null))
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("参数错误");
            return mobileRestFulModel;
        }
        Member member = memberService.find(growthDiaryShareBean.getMemberId());
        if (member == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知用户");
            return mobileRestFulModel;
        }
        com.sammyun.entity.gd.GrowthDiary growthDiary = growthDiaryService.find(growthDiaryShareBean.getGrowthDiaryId());
        if (growthDiary == null)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("未知成长记");
            return mobileRestFulModel;
        }
        DiaryTranspond diaryTranspond = new DiaryTranspond();
        diaryTranspond.setCreateDate(new Date());
        diaryTranspond.setGrowthDiary(growthDiary);
        diaryTranspond.setMember(member);
        diaryTranspond.setTranspondTime(new Date());
        // 设置分享平台
        String shared = growthDiaryShareBean.getShared();
        if (shared.equals(Shared.weixin.toString()))
        {
            diaryTranspond.setShared(Shared.weixin);
        }
        diaryTranspondService.save(diaryTranspond);

        growthDiary.setTranspondCount(growthDiary.getTranspondCount() + 1);
        growthDiaryService.update(growthDiary);

        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("分享保存成功");
        mobileRestFulModel.setRows(growthDiaryShareBean.getGrowthDiaryId());
        return mobileRestFulModel;
    }

    /**
     * 下发当前登陆者的用过的标签 <功能详细描述>
     * 
     * @param memberId
     * @return
     * @see [类、类#方法、类#成员]
     */
    // @GET
    // @Path("/v1/getDiaryTag/{memberId}")
    // @Produces("application/json;charset=UTF-8")
    // public ListRestFulModel getDiaryTag(@PathParam("memberId") Long
    // memberId){
    // ListRestFulModel listRestFulModel = new ListRestFulModel();
    // Member member = memberService.find(memberId);
    // if(member==null){
    // listRestFulModel.setResultCode(1);
    // listRestFulModel.setResultMessage("未知用户");
    // return listRestFulModel;
    // }
    // List<com.sammyun.entity.gd.GrowthDiary> growthDiaries =
    // growthDiaryService.findByMember(member);
    // if(growthDiaries==null||growthDiaries.size()==0){
    // listRestFulModel.setResultCode(0);
    // listRestFulModel.setResultMessage("无历史标签");
    // return listRestFulModel;
    // }
    // List<DiaryTag> diaryTags = new ArrayList<DiaryTag>();
    // for(com.sammyun.entity.gd.GrowthDiary growthDiary:growthDiaries){
    // Set<DiaryTag> tags = growthDiary.getDiaryTags();
    // diaryTags.addAll(tags);
    // }
    // if(diaryTags.size()==0){
    // listRestFulModel.setResultCode(0);
    // listRestFulModel.setResultMessage("无历史标签");
    // return listRestFulModel;
    // }
    // HashSet<DiaryTag> h = new HashSet<DiaryTag>(diaryTags);
    // diaryTags.clear();
    // diaryTags.addAll(h);
    // List<AppBlock> rows = new ArrayList<AppBlock>();
    // for(DiaryTag diaryTag:diaryTags){
    // AppBlock diaryTagListBlock = new AppBlock();
    // diaryTagListBlock.setDiaryTagId(diaryTag.getId());
    // diaryTagListBlock.setName(diaryTag.getName());
    // rows.add(diaryTagListBlock);
    // }
    // listRestFulModel.setRows(rows);
    // listRestFulModel.setResultCode(0);
    // listRestFulModel.setResultMessage("获取历史标签成功");
    // return listRestFulModel;
    // }

    /**
     * 获取标签 <功能详细描述>
     * 
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/getDiaryTag")
    @Produces("application/json;charset=UTF-8")
    public ListRestFulModel getDiaryTag()
    {
        ListRestFulModel listRestFulModel = new ListRestFulModel();
        List<DiaryTag> diaryTags = diaryTagService.findAll();
        if (diaryTags == null || diaryTags.size() == 0)
        {
            listRestFulModel.setResultCode(1);
            listRestFulModel.setResultMessage("暂无标签");
            return listRestFulModel;
        }
        List<DiaryTagListBlock> rows = new ArrayList<DiaryTagListBlock>();
        for (DiaryTag diaryTag : diaryTags)
        {
            DiaryTagListBlock diaryTagListBlock = new DiaryTagListBlock();
            diaryTagListBlock.setDiaryTagId(diaryTag.getId());
            diaryTagListBlock.setName(diaryTag.getName());
            rows.add(diaryTagListBlock);
        }
        listRestFulModel.setRows(rows);
        listRestFulModel.setResultCode(0);
        listRestFulModel.setResultMessage("获取历史标签成功");

        return listRestFulModel;
    }

    /**
     * 删除接口 <功能详细描述>
     * 
     * @param growthDiaryId
     * @return
     * @see [类、类#方法、类#成员]
     */
    @GET
    @Path("/v1/delete/{growthDiaryId}")
    @Produces("application/json;charset=UTF-8")
    public MobileRestFulModel delete(@PathParam("growthDiaryId") Long growthDiaryId)
    {
        MobileRestFulModel mobileRestFulModel = new MobileRestFulModel();
        try
        {
            growthDiaryService.delete(growthDiaryId);
        }
        catch (Exception e)
        {
            mobileRestFulModel.setResultCode(1);
            mobileRestFulModel.setResultMessage("删除失败");
            return mobileRestFulModel;
        }
        mobileRestFulModel.setResultCode(0);
        mobileRestFulModel.setResultMessage("删除成功");
        return mobileRestFulModel;
    }

}
