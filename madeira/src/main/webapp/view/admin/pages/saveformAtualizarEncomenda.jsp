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
        <li><a href="/madeira/view/admin/index.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
        <li class="active">Atualizar Encomenda</li>
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
          	<div class="box box-primary">
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
            <form role="form" method="post" action="/madeira/ServletItemEncomenda">
              <div class="box-body">
              	<div class="form-group">
                  <input type="text" class="form-control idinput" value="${ encomenda.getId() }" id="encomendaId" name="encomendaId">
                </div>
              	<div class="form-group">
                  <label for="movelName">Data</label>
                  <input type="datetime-local" class="form-control" id="data" name="data" value="${ encomenda.getData() }">
                </div>
                <div class="form-group">
                  <label for="movelModelo">Data prevista</label>
                  <input type="datetime-local" class="form-control" id="dataPrevista" name="dataPrevista" value="${ encomenda.dataPrevista }">
                </div>
                <div class="form-group">
                  <label for="movelDescricao">Data de entrega</label>
                  <input type="datetime-local" class="form-control" id="dataEntrega" name="dataEntrega" value="${ encomenda.dataEntrega }">
                </div>
                <div class="form-group">
                  <label for="movelPreco">Data de vencimento</label>
                  <input type="datetime-local" step=any class="form-control" id="dataVencimento" name="dataVencimento" value="${ encomenda.dataVencimento }">
                </div>
                <div class="form-group">
                  <label for="movelPreco">Data de pagamento</label>
                  <input type="datetime-local" step=any class="form-control" id="dataPagamento" name="dataPagamento" value="${ encomenda.dataPagamento }">
                </div>
                <div class="form-group">
                  <label for="movelPreco">Data de prontidão</label>
                  <input type="datetime-local" step=any class="form-control" id="dataProntidao" name="dataProntidao" value="${ encomenda.dataProntidao }">
                </div>
                <div class="form-group">
                  <label for="cliente">Cliente</label>
                  <select id="cliente" class="form-control" name="cliente">
                    <c:forEach var="cliente" items="${clientes}">
						<c:choose>
						    <c:when test="${ encomenda.getCliente().getId() == cliente.id }">
						        <option value="${ cliente.getId() }" selected="selected">${ cliente.getNome() } </option>
						    </c:when>    
						    <c:otherwise>
						        <option value="${ cliente.getId() }" >${ cliente.getNome() } </option>
						    </c:otherwise>
						</c:choose>
					</c:forEach>
                  </select>
                </div>
                <div class="form-group">
                  <label for="qtdEncomendada">Quantidade encomendada</label>
                  <input type="number" class="form-control" id="qtdEncomendada" name="qtdEncomendada" value="${ iEncomenda.getQuantidade() }">
                </div>
                <div class="form-group">
                  <label for="idMovel">Móvel</label>
                  <select id="idMovel" class="form-control" name="idMovel">
                    <c:forEach var="movel" items="${moveis}">
						<c:choose>
						    <c:when test="${ iEncomenda.getMovel().getId() == movel.id }">
						        <option value="${ movel.getId() }" selected="selected">${ movel.getNome() } </option>
						    </c:when>    
						    <c:otherwise>
						        <option value="${ movel.getId() }" >${ movel.getNome() } </option>
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