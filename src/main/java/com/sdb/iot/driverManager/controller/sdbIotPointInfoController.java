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
import com.sdb.iot.driverManager.domain.sdbIotPointInfo;
import com.sdb.iot.driverManager.service.IsdbIotPointInfoService;
import com.sdb.base.utils.poi.ExcelUtil;
import com.sdb.base.core.page.TableDataInfo;

/**
 * 位号属性Controller
 * 
 * @author sdb
 * @date 2021-10-19
 */
@RestController
@RequestMapping("/driverManager/pointInfo")
public class sdbIotPointInfoController extends BaseController
{
    @Autowired
    private IsdbIotPointInfoService sdbIotPointInfoService;

    /**
     * 查询位号属性列表
     */
    @PreAuthorize("@ss.hasPermi('driverManager:pointInfo:list')")
    @GetMapping("/list")
    public TableDataInfo list(sdbIotPointInfo sdbIotPointInfo)
    {
        startPage();
        List<sdbIotPointInfo> list = sdbIotPointInfoService.selectsdbIotPointInfoList(sdbIotPointInfo);
        return getDataTable(list);
    }

    /**
     * 导出位号属性列表
     */
    @PreAuthorize("@ss.hasPermi('driverManager:pointInfo:export')")
    @Log(title = "位号属性", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(sdbIotPointInfo sdbIotPointInfo)
    {
        List<sdbIotPointInfo> list = sdbIotPointInfoService.selectsdbIotPointInfoList(sdbIotPointInfo);
        ExcelUtil<sdbIotPointInfo> util = new ExcelUtil<sdbIotPointInfo>(sdbIotPointInfo.class);
        return util.exportExcel(list, "位号属性数据");
    }

    /**
     * 获取位号属性详细信息
     */
    @PreAuthorize("@ss.hasPermi('driverManager:pointInfo:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return AjaxResult.success(sdbIotPointInfoService.selectsdbIotPointInfoById(id));
    }

    /**
     * 新增位号属性
     */
    @PreAuthorize("@ss.hasPermi('driverManager:pointInfo:add')")
    @Log(title = "位号属性", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody sdbIotPointInfo sdbIotPointInfo)
    {
        return toAjax(sdbIotPointInfoService.insertsdbIotPointInfo(sdbIotPointInfo));
    }

    /**
     * 修改位号属性
     */
    @PreAuthorize("@ss.hasPermi('driverManager:pointInfo:edit')")
    @Log(title = "位号属性", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody sdbIotPointInfo sdbIotPointInfo)
    {
        return toAjax(sdbIotPointInfoService.updatesdbIotPointInfo(sdbIotPointInfo));
    }

    /**
     * 删除位号属性
     */
    @PreAuthorize("@ss.hasPermi('driverManager:pointInfo:remove')")
    @Log(title = "位号属性", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sdbIotPointInfoService.deletesdbIotPointInfoByIds(ids));
    }
}
