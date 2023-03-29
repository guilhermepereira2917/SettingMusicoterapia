function adicionaDatatable(idTabela) {
    dataTable = PF(idTabela);    
    
    dataTable.getPaginator().setPage(dataTable.getPaginator().cfg.pageCount - 1);
    dataTable.switchToRowEdit(dataTable.findRow(dataTable.tbody[0].children.length - 1));
}