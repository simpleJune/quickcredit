package com.free.platform.paginator;

import com.free.platform.paginator.domain.PageRequest;
import com.free.platform.paginator.domain.PageResponse;

public interface PageSearchHandler {
	public PageResponse getData(PageRequest page);
}
