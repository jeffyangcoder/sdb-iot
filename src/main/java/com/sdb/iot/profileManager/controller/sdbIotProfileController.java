package com.sdb.iot.profileManager.controller;

import java.util.List;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sdb.base.annotation.Log;
import com.sdb.base.core.controller.BaseController;
import com.sdb.base.core.domain.AjaxResult;
import com.sdb.base.enums.BusinessType;
import com.sdb.iot.profileManager.domain.sdbIotProfile;
import com.sdb.iot.profileManager.service.IsdbIotProfileService;
import com.sdb.base.utils.poi.ExcelUtil;
import com.sdb.base.core.page.TableDataInfo;

/**
 * 模板Controller
 * 
 * @author sdb
 * @date 2021-10-19
 */
@RestController
@RequestMapping("/profileManager/profile")
public class sdbIotProfileController extends BaseController
{
    @Autowired
    private IsdbIotProfileService sdbIotProfileService;

    /**
     * 查询模板列表
     */
    @PreAuthorize("@ss.hasPermi('profileManager:profile:list')")
    @GetMapping("/list")
    public TableDataInfo list(sdbIotProfile sdbIotProfile)
    {
        startPage();
        List<sdbIotProfile> list = sdbIotProfileService.selectsdbIotProfileList(sdbIotProfile);
        return getDataTable(list);
    }

    /**
     * 导出模板列表
     */
    @PreAuthorize("@ss.hasPermi('profileManager:profile:export')")
    @Log(title = "模板", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(sdbIotProfile sdbIotProfile)
    {
        List<sdbIotProfile> list = sdbIotProfileService.selectsdbIotProfileList(sdbIotProfile);
        ExcelUtil<sdbIotProfile> util = new ExcelUtil<sdbIotProfile>(sdbIotProfile.class);
        return util.exportExcel(list, "模板数据");
    }

    /**
     * 获取模板详细信息
     */
    @PreAuthorize("@ss.hasPermi('profileManager:profile:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sdbIotProfileService.selectsdbIotProfileById(id));
    }

    /**
     * 新增模板
     */
    @PreAuthorize("@ss.hasPermi('profileManager:profile:add')")
    @Log(title = "模板", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody sdbIotProfile sdbIotProfile)
    {
        return toAjax(sdbIotProfileService.insertsdbIotProfile(sdbIotProfile));
    }

    /**
     * 修改模板
     */
    @PreAuthorize("@ss.hasPermi('profileManager:profile:edit')")
    @Log(title = "模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody sdbIotProfile sdbIotProfile)
    {
        return toAjax(sdbIotProfileService.updatesdbIotProfile(sdbIotProfile));
    }

    /**
     * 删除模板
     */
    @PreAuthorize("@ss.hasPermi('profileManager:profile:remove')")
    @Log(title = "模板", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sdbIotProfileService.deletesdbIotProfileByIds(ids));
    }
}
