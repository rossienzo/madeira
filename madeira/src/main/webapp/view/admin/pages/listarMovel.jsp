  
  <%@ include file="./layout/header.jsp" %>

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Dashboard
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="/madeira/ServletIndex"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Listar M?veis</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Main row -->
      <div class="row">
        <!-- Left col -->
        <div class="col-xs-12">
        	<div class="box box-danger">
            <div class="box-header">
              <h3 class="box-title">Listar M?veis</h3>
            </div>
			<c:if test="${ msg.text != null}">
				<div class="box-header msg ${ msg.type }">
	              <h3 class="box-title">${ msg.text }</h3>
	            </div>
			</c:if>
            
            <!-- /.box-header -->
            <div class="box-body">
            	
	              <div id="example1_wrapper" class="dataTables_wrapper form-inline dt-bootstrap"><div class="row">
		              <div class="col-sm-6">
			              <div class="dataTables_length" id="example1_length">
			              	<label>Show <select name="length" aria-controls="example1" class="form-control input-sm"><option value="10">10</option><option value="25">25</option><option value="50">50</option><option value="100">100</option></select> entries</label>
			              </div>
		              </div>
		              <div class="col-sm-6">
			              <div id="example1_filter" class="dataTables_filter">
		              		<label>Search:<input type="search" id="inputSearch" name="buscar" onkeypress="search(this, 'ServletMovel')" class="form-control input-sm" placeholder="" aria-controls="example1"></label>		
			              </div>
		              </div>
	              </div>
	              <div class="row"><div class="col-sm-12"><table id="example1" class="table table-bordered table-striped dataTable" role="grid" aria-describedby="example1_info">
	                <thead>
	                	<tr role="row">
	                		<th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending" style="width: 85px;">Nome do M?vel</th>
	                		<th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending" style="width: 91px;">Modelo</th>
	                		<th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="Engine version: activate to sort column ascending" style="width: 67px;">Descri??o</th>
	                		<th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="CSS grade: activate to sort column ascending" style="width: 47px;">Pre?o</th>
	               			<th class="sorting" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-label="CSS grade: activate to sort column ascending" style="width: 47px;">Linha M?vel</th>
	               			<th class="sorting_asc" tabindex="0" aria-controls="example1" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending" style="width: 25px;"></th>
	               		</tr>
	                </thead>
	                <tbody>
	                	<c:forEach var="movel" items="${moveis}">
							<tr role="row" name="${ movel.id }" class="odd">				
			                  <td class="sorting_1">${ movel.nome }</td>
			                  <td>${ movel.modelo }</td>
			                  <td>${ movel.descricao }</td>
			                  <td>${ movel.preco }</td>
			                  <td title="${ movel.getLinhaMovel().getDescricao() }">${ movel.getLinhaMovel().getNome() }</td>
			                  <td class="sorting_1">
			                  	<button id="btnEditar" type="submit" name="editar" class="btn btn-warning" onclick="action(this, 'ServletMovel')">
								  <i class="fa fa-edit"></i>
								</button>	
								<button id="btnRemover" type="submit" name="deletar" class="btn btn-primary" onclick="action(this, 'ServletMovel')">
								  <i class="fa fa-trash"></i>
								</button>
								</td>	
		                	</tr>
						</c:forEach>                 
		                
	              	</tbody>
	              </table></div></div><div class="row"><div class="col-sm-5"><div class="dataTables_info" id="example1_info" role="status" aria-live="polite">Showing 1 to 10 of 57 entries</div></div><div class="col-sm-7"><div class="dataTables_paginate paging_simple_numbers" id="example1_paginate"><ul class="pagination"><li class="paginate_button previous disabled" id="example1_previous"><a href="#" aria-controls="example1" data-dt-idx="0" tabindex="0">Previous</a></li><li class="paginate_button active"><a href="#" aria-controls="example1" data-dt-idx="1" tabindex="0">1</a></li><li class="paginate_button "><a href="#" aria-controls="example1" data-dt-idx="2" tabindex="0">2</a></li><li class="paginate_button "><a href="#" aria-controls="example1" data-dt-idx="3" tabindex="0">3</a></li><li class="paginate_button "><a href="#" aria-controls="example1" data-dt-idx="4" tabindex="0">4</a></li><li class="paginate_button "><a href="#" aria-controls="example1" data-dt-idx="5" tabindex="0">5</a></li><li class="paginate_button "><a href="#" aria-controls="example1" data-dt-idx="6" tabindex="0">6</a></li><li class="paginate_button next" id="example1_next"><a href="#" aria-controls="example1" data-dt-idx="7" tabindex="0">Next</a></li></ul></div></div></div></div>
            </div>
            <!-- /.box-body -->
          </div>
        </div>
        <!-- right col -->
      </div>
      <!-- /.row (main row) -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->
  
   <%@ include file="./layout/footer.jsp" %>