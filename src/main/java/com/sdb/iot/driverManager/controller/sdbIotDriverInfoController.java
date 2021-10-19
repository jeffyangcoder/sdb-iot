package com.sdb.iot.driverManager.controller;

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
import com.sdb.iot.driverManager.domain.sdbIotDriverInfo;
import com.sdb.iot.driverManager.service.IsdbIotDriverInfoService;
import com.sdb.base.utils.poi.ExcelUtil;
import com.sdb.base.core.page.TableDataInfo;

/**
 * 驱动配置Controller
 * 
 * @author sdb
 * @date 2021-10-18
 */
@RestController
@RequestMapping("/driverManager/driverInfo")
public class sdbIotDriverInfoController extends BaseController
{
    @Autowired
    private IsdbIotDriverInfoService sdbIotDriverInfoService;

    /**
     * 查询驱动配置列表
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(sdbIotDriverInfo sdbIotDriverInfo)
    {
        startPage();
        List<sdbIotDriverInfo> list = sdbIotDriverInfoService.selectsdbIotDriverInfoList(sdbIotDriverInfo);
        return getDataTable(list);
    }

    /**
     * 导出驱动配置列表
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverInfo:export')")
    @Log(title = "驱动配置", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(sdbIotDriverInfo sdbIotDriverInfo)
    {
        List<sdbIotDriverInfo> list = sdbIotDriverInfoService.selectsdbIotDriverInfoList(sdbIotDriverInfo);
        ExcelUtil<sdbIotDriverInfo> util = new ExcelUtil<sdbIotDriverInfo>(sdbIotDriverInfo.class);
        return util.exportExcel(list, "驱动配置数据");
    }

    /**
     * 获取驱动配置详细信息
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sdbIotDriverInfoService.selectsdbIotDriverInfoById(id));
    }

    /**
     * 新增驱动配置
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverInfo:add')")
    @Log(title = "驱动配置", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody sdbIotDriverInfo sdbIotDriverInfo)
    {
        return toAjax(sdbIotDriverInfoService.insertsdbIotDriverInfo(sdbIotDriverInfo));
    }

    /**
     * 修改驱动配置
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverInfo:edit')")
    @Log(title = "驱动配置", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody sdbIotDriverInfo sdbIotDriverInfo)
    {
        return toAjax(sdbIotDriverInfoService.updatesdbIotDriverInfo(sdbIotDriverInfo));
    }

    /**
     * 删除驱动配置
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverInfo:remove')")
    @Log(title = "驱动配置", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sdbIotDriverInfoService.deletesdbIotDriverInfoByIds(ids));
    }
}
