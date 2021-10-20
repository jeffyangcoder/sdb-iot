package com.sdb.iot.groupManager.controller;

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
import com.sdb.iot.groupManager.domain.sdbIotGroup;
import com.sdb.iot.groupManager.service.IsdbIotGroupService;
import com.sdb.base.utils.poi.ExcelUtil;
import com.sdb.base.core.page.TableDataInfo;

/**
 * 分组Controller
 * 
 * @author sdb
 * @date 2021-10-20
 */
@RestController
@RequestMapping("/groupManager/group")
public class sdbIotGroupController extends BaseController
{
    @Autowired
    private IsdbIotGroupService sdbIotGroupService;

    /**
     * 查询分组列表
     */
    @PreAuthorize("@ss.hasPermi('groupManager:group:list')")
    @GetMapping("/list")
    public TableDataInfo list(sdbIotGroup sdbIotGroup)
    {
        startPage();
        List<sdbIotGroup> list = sdbIotGroupService.selectsdbIotGroupList(sdbIotGroup);
        return getDataTable(list);
    }

    /**
     * 导出分组列表
     */
    @PreAuthorize("@ss.hasPermi('groupManager:group:export')")
    @Log(title = "分组", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(sdbIotGroup sdbIotGroup)
    {
        List<sdbIotGroup> list = sdbIotGroupService.selectsdbIotGroupList(sdbIotGroup);
        ExcelUtil<sdbIotGroup> util = new ExcelUtil<sdbIotGroup>(sdbIotGroup.class);
        return util.exportExcel(list, "分组数据");
    }

    /**
     * 获取分组详细信息
     */
    @PreAuthorize("@ss.hasPermi('groupManager:group:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sdbIotGroupService.selectsdbIotGroupById(id));
    }

    /**
     * 新增分组
     */
    @PreAuthorize("@ss.hasPermi('groupManager:group:add')")
    @Log(title = "分组", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody sdbIotGroup sdbIotGroup)
    {
        return toAjax(sdbIotGroupService.insertsdbIotGroup(sdbIotGroup));
    }

    /**
     * 修改分组
     */
    @PreAuthorize("@ss.hasPermi('groupManager:group:edit')")
    @Log(title = "分组", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody sdbIotGroup sdbIotGroup)
    {
        return toAjax(sdbIotGroupService.updatesdbIotGroup(sdbIotGroup));
    }

    /**
     * 删除分组
     */
    @PreAuthorize("@ss.hasPermi('groupManager:group:remove')")
    @Log(title = "分组", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sdbIotGroupService.deletesdbIotGroupByIds(ids));
    }
}
