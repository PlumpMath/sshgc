(ns sshgc.dialog
  (:gen-class)
  (:import (tabs FXDialog Message)))

(defn info
  "Show information dialog."
  [title msg]
  (FXDialog/showMessageDialog msg title Message/INFORMATION))

(defn error
  "Show error dialog."
  [title msg]
  (FXDialog/showMessageDialog msg title Message/ERROR))

(defn warning
  "Show warning dialog."
  [title msg]
  (FXDialog/showMessageDialog msg title Message/WARNING))
