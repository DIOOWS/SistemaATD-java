// Função para listar produtos
export const listarProdutos = async () => {
  const response = await fetch('http://localhost:8080/estoque');
  const produtos = await response.json();
  return produtos;
};

// Função para adicionar produto
export const adicionarProduto = async (produto) => {
  const response = await fetch('http://localhost:8080/estoque', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(produto),
  });
  return response.json();
};

// Função para atualizar produto
export const atualizarProduto = async (produto) => {
  const response = await fetch(`http://localhost:8080/estoque/${produto.id}`, {
    method: 'PUT',
    headers: {
      'Content-Type': 'application/json',
    },
    body: JSON.stringify(produto),
  });
  return response.json();
};

// Função para deletar produto
export const deletarProduto = async (id) => {
  const response = await fetch(`http://localhost:8080/estoque/${id}`, {
    method: 'DELETE',
  });
  return response.json();
};
