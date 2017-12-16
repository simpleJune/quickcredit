package com.free.platform.paginator.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.free.platform.paginator.PageDecoratorHandler;
import com.free.platform.paginator.ResultFormateConfig;
import com.free.platform.paginator.ValueProcessorDecoratorHandler;
import com.free.platform.paginator.processor.DefaultDateProcessor;
import com.free.platform.paginator.processor.DefaultDecimalProcessor;

/**
 * 
 * 包含“分页”信息的List
 * 
 * <p>
 * 要得到总页数请使用 toPaginator().getTotalPages();
 * </p>
 *
 */
public class PageList<E> extends ArrayList<E> {
	private static final long serialVersionUID = -488594734646660369L;
	private Paginator paginator;

	public PageList() {
	}

	public void setPaginator(Paginator paginator) {
		this.paginator = paginator;
	}

	public PageList(Collection<? extends E> c, Paginator p) {
		super(c);
		this.paginator = p;
	}

	public PageList(Paginator p) {
		this.paginator = p;
	}

	/**
	 * 得到分页器，通过Paginator可以得到总页数等值
	 * 
	 * @return
	 */
	public Paginator getPaginator() {
		return paginator;
	}

	public PageResponse toPageResponse() {
		PageResponse resp = new PageResponse();
		resp.setRows(this);
		if (this.paginator != null) {
			resp.setTotal(this.paginator.getTotalCount());
		}
		return resp;
	}
	


	public PageResponse toPageResponseDefaultFormate() {
		ResultFormateConfig formateConfig = new ResultFormateConfig();
		DefaultDateProcessor dateProcessor = new DefaultDateProcessor();
		DefaultDecimalProcessor decimalProcessor = new DefaultDecimalProcessor();

		formateConfig.registerResultValueProcessor(Date.class, dateProcessor);
		formateConfig.registerResultValueProcessor(BigDecimal.class,
				decimalProcessor);
		formateConfig.registerResultValueProcessor(Double.class,
				decimalProcessor);
		formateConfig.registerResultValueProcessor(Float.class,
				decimalProcessor);
		return toPageResponseFormate(formateConfig);
	}

	public PageResponse toPageResponseFormate(ResultFormateConfig formateConfig) {
		return toPageResponse(new ValueProcessorDecoratorHandler(formateConfig));
	}

	public <T>PageResponse toPageResponse(PageDecoratorHandler<T> decorator) {
		PageResponse resp = new PageResponse();
		List<T> list = new ArrayList<T>();
		if (this.size() > 0) {
			for (Object obj : this) {
				list.add(decorator.invoke(obj));
			}
		}
		resp.setRows(list);
		if (this.paginator != null) {
			resp.setTotal(this.paginator.getTotalCount());
		}
		return resp;
	}
	
}
