package model;

import java.util.List;

public class PaginacaoPessoaModel {

    private Integer totalElements;
    private Integer totalPages;
    private Integer page;
    private Integer size;
    private List<PessoaModel> content;

    public Integer getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Integer totalElements) {
        this.totalElements = totalElements;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<PessoaModel> getContent() {
        return content;
    }

    public void setContent(List<PessoaModel> content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PaginacaoModel{" +
                "totalElements=" + totalElements +
                ", totalPages=" + totalPages +
                ", page=" + page +
                ", size=" + size +
                ", content=" + content +
                '}';
    }
}