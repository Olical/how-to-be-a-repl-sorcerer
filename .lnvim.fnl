(module repos.olical.how-to-be-a-repl-sourcerer
  {require {nvim aniseed.nvim}})

(set nvim.g.conjure#client#clojure#nrepl#refresh#before :sorcery.main/before)
(set nvim.g.conjure#client#clojure#nrepl#refresh#after :sorcery.main/after)
