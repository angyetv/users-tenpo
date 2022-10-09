package cl.challenge.tenpo.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PagedResponse<T> {

    private List<T> content;
    private int totalPages;
    private int pageSize;
    private long totalElements;
    private int pageNumber;

    public PagedResponse(Page<T> page) {
        this.content = page.getContent();
        this.totalPages = page.getTotalPages();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
        this.pageNumber = page.getNumber();
    }
}
