package com.xmg.pss.web.action;

import java.io.File;

import org.apache.commons.lang3.StringUtils;

import com.xmg.pss.domain.Product;
import com.xmg.pss.query.ProductQueryObject;
import com.xmg.pss.service.IBrandService;
import com.xmg.pss.service.IProductService;
import com.xmg.pss.util.FileUploadUtil;
import com.xmg.pss.util.RequiredPermission;

import lombok.Getter;
import lombok.Setter;

public class ProductAction extends BaseAction {
	private static final long serialVersionUID = 1L;

	@Setter
	private IProductService productService;
	
	@Setter
	private IBrandService brandService; 

	@Getter
	private ProductQueryObject qo=new ProductQueryObject();

	@Getter
	private Product product = new Product();
	
	@Setter
	private File pic;//接收上传的文件
	
	@Setter
	private String picFileName;//上传的文件名称
	
	@RequiredPermission("货品管理列表")
	public String execute(){
		try {
			putContext("result", productService.pageQuery(qo));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return LIST;
	}

	@RequiredPermission("货品管理编辑")
	public String input() {
		try {
			putContext("brands", brandService.list());
			if (product.getId() != null) {
                product = productService.get(product.getId());
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return INPUT;
	}

	@RequiredPermission("货品管理保存/更新")
	public String saveOrUpdate() {
		try {
			if (pic!=null) {
				
				String uploadFile = FileUploadUtil.uploadFile(pic, picFileName);
				product.setImagePath(uploadFile);
			}
			if (product.getId() == null) {
                productService.save(product);
				addActionMessage("增加成功");
            } else {
                productService.update(product);
				addActionMessage("更新成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return SUCCESS;
	}

	@RequiredPermission("货品管理删除")
	public String delete()  throws  Exception {
		try {
			if (product.getId() != null) {
				product = productService.get(product.getId());
				if (StringUtils.isNotEmpty(product.getImagePath())) {
					FileUploadUtil.deleteFile(product.getImagePath());
				}
                productService.delete(product.getId());
				putMsg("删除成功");
            }
		} catch (Exception e) {
			e.printStackTrace();
			putMsg(e.getMessage());
		}
		return NONE;
	}

}
