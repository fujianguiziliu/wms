package com.xmg.pss.web.action;

import com.xmg.pss.domain.ProductStock;
import com.xmg.pss.query.ProductStockQueryObject;
import com.xmg.pss.service.IProductStockService;
import com.xmg.pss.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class ProductStockAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IProductStockService productStockService;

	@Getter
	private ProductStockQueryObject qo=new ProductStockQueryObject();

	@Getter
	private ProductStock productStock = new ProductStock();
	
	@RequiredPermission("产品库存列表")
	public String execute(){
		try {
			putContext("result", productStockService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("产品库存编辑")
	public String input() {
		try {
			if (productStock.getId() != null) {
                productStock = productStockService.get(productStock.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("产品库存保存/更新")
	public String saveOrUpdate() {
		try {
			if (productStock.getId() == null) {
                productStockService.save(productStock);
				addActionMessage("增加成功");
            } else {
                productStockService.update(productStock);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("产品库存删除")
	public String delete()  throws  Exception {
		try {
			if (productStock.getId() != null) {
                productStockService.delete(productStock.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}
