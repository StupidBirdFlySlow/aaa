package edu.hunau.base.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Page<T>
  extends HashMap<String, Object>
  implements Serializable
{
  private static final long serialVersionUID = -4090447194927780409L;
  private static int DEFAULT_PAGE_SIZE = 20;
  private static String DEFAULT_PAGE_SIZE_NAME = "size";
  private static String DEFAULT_ITEMS_NAME = "rows";
  private static String DEFAULT_TOTALROWS_NAME = "records";
  private static String DEFAULT_CURRENT_PAGE_NO_NAME = "page";
  private static String DEFAULT_TOTAL_PAGE_COUNT_NAME = "total";
  private int pageSize = DEFAULT_PAGE_SIZE;
  private int start;
  private List<T> data;
  private int totalCount;
  public static final Page EMPTY = new Page();
  
  public Page()
  {
    this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
  }
  
  public Page(int start, int totalSize, int pageSize, List<T> data)
  {
    super(0);
    this.pageSize = pageSize;
    this.start = start;
    this.totalCount = totalSize;
    this.data = data;
    put(DEFAULT_TOTALROWS_NAME, Integer.valueOf(totalSize));
    put(DEFAULT_ITEMS_NAME, data);
    put(DEFAULT_CURRENT_PAGE_NO_NAME, Integer.valueOf(getCurrentPageNo()));
    put(DEFAULT_TOTAL_PAGE_COUNT_NAME, Integer.valueOf(getTotalPageCount()));
    put(DEFAULT_PAGE_SIZE_NAME, Integer.valueOf(getPageSize()));
  }
  
  public int getTotalCount()
  {
    return this.totalCount;
  }
  
  public int getTotalPageCount()
  {
    if ((this.pageSize == 0) || (this.totalCount == 0)) {
      return 1;
    }
    if (this.totalCount % this.pageSize == 0) {
      return this.totalCount / this.pageSize;
    }
    return this.totalCount / this.pageSize + 1;
  }
  
  public int getPageSize()
  {
    return this.pageSize;
  }
  
  public List<T> getResult()
  {
    return this.data;
  }
  
  public void setResult(List<T> result)
  {
    this.data = result;
    put(DEFAULT_ITEMS_NAME, this.data);
  }
  
  public int getCurrentPageNo()
  {
    if (this.pageSize == 0) {
      return 1;
    }
    return this.start / this.pageSize + 1;
  }
  
  public int getStart()
  {
    return this.start;
  }
  
  public boolean hasNextPage()
  {
    return getCurrentPageNo() < getTotalPageCount() - 1;
  }
  
  public boolean hasPreviousPage()
  {
    return getCurrentPageNo() > 1;
  }
  
  protected static int getStartOfPage(int pageNo)
  {
    return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
  }
  
  public static int getStartOfPage(int pageNo, int pageSize)
  {
    return (pageNo - 1) * pageSize;
  }
}
