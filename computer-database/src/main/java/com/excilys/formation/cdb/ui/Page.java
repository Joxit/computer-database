package com.excilys.formation.cdb.ui;

import java.util.List;

import com.excilys.formation.cdb.persistence.IComputerDAO.OrderBy;
import com.excilys.formation.cdb.service.Paginable;

public class Page<T> {
	private final Paginable<T> paginable;
	private int nbItems;
	private int curPage;
	private int limit;
	private int previous;
	private int next;
	private int pageMax;
	private int firstPage;
	private int lastPage;
	private String search;
	private OrderBy ob;
	private boolean asc;

	public Page(Paginable<T> paginable) {
		this(paginable, "", 0, 1, 10, 5);
	}

	public Page(Paginable<T> paginable, int nbItems, int curPage, int limit,
			int pageLimit) {
		this(paginable, "", nbItems, curPage, limit, pageLimit);
	}

	public Page(Paginable<T> paginable, String search, int nbItems,
			int curPage, int limit, int pageLimit) {
		this(paginable, search, nbItems, curPage, limit, pageLimit, OrderBy.ID,
				true);
	}

	public Page(Paginable<T> paginable, String search, int nbItems,
			int curPage, int limit, int pageLimit, OrderBy ob, boolean asc) {
		this.paginable = paginable;
		this.nbItems = nbItems;
		this.curPage = curPage;
		this.limit = limit;
		this.search = search == null ? "" : search;
		this.ob = ob == null ? OrderBy.ID : ob;
		this.asc = asc;
		if (this.limit < 1) {
			this.limit = 10;
		}

		if ((((this.curPage - 1) * this.limit) > this.nbItems)
				|| (this.curPage < 1)) {
			this.curPage = 1;
		}

		pageMax = (this.nbItems / this.limit)
				+ ((this.nbItems % this.limit) == 0 ? 0 : 1);
		firstPage = Integer.max(this.curPage - 2, 1);
		lastPage = Integer.min(firstPage + (pageLimit - 1), pageMax);
		firstPage = lastPage - (pageLimit - 1);
		if (firstPage < 1) {
			firstPage = 1;
		}

		previous = (this.curPage - 1) < 1 ? 1 : this.curPage - 1;
		next = (this.curPage + 1) > pageMax ? pageMax : this.curPage + 1;
	}

	public int getNbItems() {
		return nbItems;
	}

	public int getCurPage() {
		return curPage;
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
		return previous == curPage;
	}

	public boolean isLast() {
		return lastPage == curPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public List<T> getPage() {
		return paginable.pagination(search, limit, ((curPage - 1) * limit), ob,
				asc);
	}

	public String getSearch() {
		return search;
	}

	public OrderBy getOb() {
		return ob;
	}

	public String getObName() {
		return ob.name().toLowerCase();
	}

	public boolean isAsc() {
		return asc;
	}

	@Override
	public String toString() {
		return "Page [paginable=" + paginable + ", nbItems=" + nbItems
				+ ", curPage=" + curPage + ", limit=" + limit + ", previous="
				+ previous + ", next=" + next + ", pageMax=" + pageMax
				+ ", firstPage=" + firstPage + ", lastPage=" + lastPage
				+ ", search=" + search + "]";
	}
}
