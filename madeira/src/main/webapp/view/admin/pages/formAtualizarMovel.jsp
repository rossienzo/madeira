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
        <li class="active">Atualizar Móvel</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">

      <!-- Main row -->
      <div class="row">
        <!-- Left col -->
        <section class="col-lg-12 connectedSortable">
          <!-- Custom tabs (Charts with tabs)-->
          <div class="col-lg-12">
          	<div class="box box-danger">
            <div class="box-header with-border">
              <h3 class="box-title">Editar Móvel</h3>
            </div>
            <c:if test="${ msg.text != null}">
				<div class="box-header msg ${ msg.type }">
	              <h3 class="box-title">${ msg.text }</h3>
	            </div>
			</c:if>
            <!-- /.box-header -->
            
            <!-- form start -->
            <form role="form" method="post" action="/madeira/ServletMovel">
              <div class="box-body">
              	<div class="form-group">
                  <input type="text" class="form-control idinput" value="${ movel.id }" id="movelId" name="movelId" >
                </div>
                <div class="form-group">
                  <label for="movelName">Móvel</label>
                  <input type="text" class="form-control" value="${ movel.nome }" id="movelName" name="movelName" placeholder="Digite o nome do móvel">
                </div>
                <div class="form-group">
                  <label for="movelModelo">Modelo</label>
                  <input type="text" class="form-control" value="${ movel.modelo }" id="movelModelo" name="movelModelo" placeholder="Digite o modelo do móvel">
                </div>
                <div class="form-group">
                  <label for="movelDescricao">Descrição</label>
                  <input type="text" class="form-control" value="${ movel.descricao }" id="movelDescricao" name="movelDescricao" placeholder="Digite a descrição do móvel">
                </div>
                <div class="form-group">
                  <label for="movelPreco">Preço</label>
                  <input type="number" step=any class="form-control" value="${ movel.preco }" id="movelPreco" name="movelPreco" placeholder="Digite o preço do móvel">
                </div>
                <div class="form-group">
                  <label for="movelLinha">Linha do móvel</label>
                  <select id="movelLinha" class="form-control" name="movelLinha">
                    <c:forEach var="linhaMovel" items="${linhasMoveis}">
						<c:choose>
						    <c:when test="${ movel.getLinhaMovel().getId() == linhaMovel.id }">
						        <option value="${ linhaMovel.id }" selected="selected">${ linhaMovel.nome }</option>
						    </c:when>    
						    <c:otherwise>
						        <option value="${ linhaMovel.id }">${ linhaMovel.nome }</option>
						    </c:otherwise>
						</c:choose>
					</c:forEach>
                  </select>
                </div>
              </div>
              <!-- /.box-body -->

              <div class="box-footer">
                <button type="submit" class="btn btn-primary">Atualizar</button>
              </div>
            </form>
          </div>
          </div>
        </section>
        <!-- right col -->
      </div>
      <!-- /.row (main row) -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <%@ include file="./layout/footer.jsp" %>