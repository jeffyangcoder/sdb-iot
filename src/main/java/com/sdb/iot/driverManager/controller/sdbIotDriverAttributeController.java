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
import com.sdb.iot.driverManager.domain.sdbIotDriverAttribute;
import com.sdb.iot.driverManager.service.IsdbIotDriverAttributeService;
import com.sdb.base.utils.poi.ExcelUtil;
import com.sdb.base.core.page.TableDataInfo;

/**
 * 驱动属性信息Controller
 * 
 * @author sdb
 * @date 2021-10-17
 */
@RestController
@RequestMapping("/driverManager/driverAttribute")
public class sdbIotDriverAttributeController extends BaseController
{
    @Autowired
    private IsdbIotDriverAttributeService sdbIotDriverAttributeService;

    /**
     * 查询驱动属性信息列表
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverAttribute:list')")
    @GetMapping("/list")
    public TableDataInfo list(sdbIotDriverAttribute sdbIotDriverAttribute)
    {
        startPage();
        List<sdbIotDriverAttribute> list = sdbIotDriverAttributeService.selectsdbIotDriverAttributeList(sdbIotDriverAttribute);
        return getDataTable(list);
    }

    /**
     * 导出驱动属性信息列表
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverAttribute:export')")
    @Log(title = "驱动属性信息", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(sdbIotDriverAttribute sdbIotDriverAttribute)
    {
        List<sdbIotDriverAttribute> list = sdbIotDriverAttributeService.selectsdbIotDriverAttributeList(sdbIotDriverAttribute);
        ExcelUtil<sdbIotDriverAttribute> util = new ExcelUtil<sdbIotDriverAttribute>(sdbIotDriverAttribute.class);
        return util.exportExcel(list, "驱动属性信息数据");
    }

    /**
     * 获取驱动属性信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverAttribute:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sdbIotDriverAttributeService.selectsdbIotDriverAttributeById(id));
    }

    /**
     * 新增驱动属性信息
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverAttribute:add')")
    @Log(title = "驱动属性信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody sdbIotDriverAttribute sdbIotDriverAttribute)
    {
        return toAjax(sdbIotDriverAttributeService.insertsdbIotDriverAttribute(sdbIotDriverAttribute));
    }

    /**
     * 修改驱动属性信息
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverAttribute:edit')")
    @Log(title = "驱动属性信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody sdbIotDriverAttribute sdbIotDriverAttribute)
    {
        return toAjax(sdbIotDriverAttributeService.updatesdbIotDriverAttribute(sdbIotDriverAttribute));
    }

    /**
     * 删除驱动属性信息
     */
    @PreAuthorize("@ss.hasPermi('driverManager:driverAttribute:remove')")
    @Log(title = "驱动属性信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sdbIotDriverAttributeService.deletesdbIotDriverAttributeByIds(ids));
    }
}
