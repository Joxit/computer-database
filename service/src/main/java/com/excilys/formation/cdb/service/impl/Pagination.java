package com.excilys.formation.cdb.service.impl;

import java.util.List;

import com.excilys.formation.cdb.persistence.IComputerDAO.OrderBy;
import com.excilys.formation.cdb.service.Paginable;

public class Pagination<T> {
	private Paginable<T> paginable;
	private int nbItems;

	private int page;
	private int limit;
	private int previous;
	private int next;
	private int pageMax;
	private int firstPage;
	private int lastPage;
	private String search;
	private OrderBy mOrderBy;
	private String orderBy;
	private int pageLimit = 5;
	private boolean asc;

	public Pagination() {
		mOrderBy = OrderBy.NAME;
		asc = true;
	}

	public void setPageLimit(int pageLimit) {
		this.pageLimit = pageLimit;
	}

	public int getNbItems() {
		return nbItems;
	}

	public int getPage() {
		return page;
	}

	public int getLimit() {
		return limit;
	}

	public int getPrevious() {
		return previous;
	}

	public int getNext() {
		return next;
	}

	public int getPageMax() {
		return pageMax;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public boolean isFirst() {
		return previous == page;
	}

	public boolean isLast() {
		return lastPage == page;
	}

	public int getLastPage() {
		return lastPage;
	}

	public List<T> getPagination() {
		return paginable.pagination(search, limit, ((page - 1) * limit),
				mOrderBy, asc);
	}

	public String getSearch() {
		return search;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public String getObName() {
		return mOrderBy.name().toLowerCase();
	}

	public boolean isAsc() {
		return asc;
	}

	@Override
	public String toString() {
		return "Pagination [paginable=" + paginable + ", nbItems=" + nbItems
				+ ", page=" + page + ", limit=" + limit + ", previous="
				+ previous + ", next=" + next + ", pageMax=" + pageMax
				+ ", firstPage=" + firstPage + ", lastPage=" + lastPage
				+ ", search=" + search + ", mOrderBy=" + mOrderBy
				+ ", orderBy=" + orderBy + ", pageLimit=" + pageLimit
				+ ", asc=" + asc + "]";
	}

	public void validate() {
		mOrderBy = OrderBy.map(orderBy);

		if (limit < 1) {
			limit = 10;
		}

		if (page < 1) {
			page = 1;
		}

		if (search == null) {
			search = "";
		}
		nbItems = paginable.count(search);

		if ((((page - 1) * limit) > nbItems) || (page < 1)) {
			page = 1;
		}

		pageMax = (nbItems / limit) + ((nbItems % limit) == 0 ? 0 : 1);
		firstPage = Integer.max(page - 2, 1);
		lastPage = Integer.min(firstPage + (pageLimit - 1), pageMax);
		firstPage = lastPage - (pageLimit - 1);
		if (firstPage < 1) {
			firstPage = 1;
		}

		previous = (page - 1) < 1 ? 1 : page - 1;
		next = (page + 1) > pageMax ? pageMax : page + 1;
	}

	public void setPaginable(Paginable<T> paginable) {
		this.paginable = paginable;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public void setNbItems(int nbItems) {
		this.nbItems = nbItems;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public void setAsc(boolean asc) {
		this.asc = asc;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
}
