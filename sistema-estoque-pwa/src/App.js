import React, { useState, useEffect } from 'react';
import { listarProdutos, adicionarProduto, atualizarProduto, deletarProduto } from './api';  // Importando funções para a API

function App() {
  const [produtos, setProdutos] = useState([]);
  const [nome, setNome] = useState('');
  const [quantidade, setQuantidade] = useState(0);
  const [produtoId, setProdutoId] = useState(null);  // Para armazenar o id do produto a ser atualizado

  useEffect(() => {
    const fetchProdutos = async () => {
      const produtosData = await listarProdutos();
      setProdutos(produtosData || []);
    };
    fetchProdutos();
  }, []);

  const handleAdicionarProduto = async () => {
    const produto = { nome, quantidade };
    await adicionarProduto(produto);
    setProdutos([...produtos, produto]);
    setNome('');
    setQuantidade(0);
  };

  const handleAtualizarProduto = async () => {
    if (produtoId) {
      const produtoAtualizado = { id: produtoId, nome, quantidade };
      await atualizarProduto(produtoAtualizado);
      setProdutos(
        produtos.map((produto) =>
          produto.id === produtoId ? { ...produto, nome, quantidade } : produto
        )
      );
      setProdutoId(null); // Limpa o id após a atualização
      setNome('');
      setQuantidade(0);
    }
  };

  const handleDeletarProduto = async (id) => {
    await deletarProduto(id);
    setProdutos(produtos.filter((produto) => produto.id !== id));
  };

  return (
    <div className="App">
      <h1>Gestão de Estoque</h1>
      <div>
        <input
          type="text"
          placeholder="Nome do Produto"
          value={nome}
          onChange={(e) => setNome(e.target.value)}
        />
        <input
          type="number"
          placeholder="Quantidade"
          value={quantidade}
          onChange={(e) => setQuantidade(e.target.value)}
        />
        <button onClick={handleAdicionarProduto}>Adicionar Produto</button>
        {produtoId && (
          <button onClick={handleAtualizarProduto}>Atualizar Produto</button>
        )}
      </div>
      <ul>
        {produtos && produtos.length > 0 ? (
          produtos.map((produto) => (
            <li key={produto.id}>
              {produto.nome} - {produto.quantidade}
              <button onClick={() => {
                setProdutoId(produto.id);
                setNome(produto.nome);
                setQuantidade(produto.quantidade);
              }}>
                Editar
              </button>
              <button onClick={() => handleDeletarProduto(produto.id)}>
                Deletar
              </button>
            </li>
          ))
        ) : (
          <li>Sem produtos cadastrados.</li>
        )}
      </ul>
    </div>
  );
}

export default App;
