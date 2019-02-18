(ns nedap.utils.io.api
  (:require
   [clojure.java.io :as io]
   [me.raynes.fs :as fs]
   [nedap.utils.spec.api :refer [check!]]))

(defn running-in-jar?
  "Is the current Clojure process running as a .jar?"
  []
  (-> *ns* .getClass (.getResource "/") nil?))

(defn copy-file-from-resource
  "Finds `resource-name` as a resource, and copies it to `filename-to`.

  The directory containing `filename-to` must exist beforehand."
  [resource-name filename-to]
  {:pre [(check! string? resource-name
                 string? filename-to)]}
  (when (and (io/resource resource-name) ;; exists in jar as a resource
             (-> filename-to io/file .exists not)) ;; does not exist in project as a file
    (let [is (-> resource-name io/resource io/input-stream)
          buffer (byte-array (-> is .available))]
      (-> is (.read buffer))
      (doto (io/output-stream (-> filename-to io/file))
        (.write buffer)
        (.close)))))

(defn ensure-directory-exists
  "Ensures that `f`, typically a filename (or anything that can be coerced to a File), exists as a directory, by creating it."
  [f]
  {:pre [f]}
  (let [dir (-> f
                io/file
                fs/absolute
                fs/parent)]
    (when-not (fs/exists? dir)
      (when-not (fs/mkdirs dir)
        (throw (ex-info (str ::ensure-output-directory-exists) {:message "Could not create directory"
                                                                :dir dir}))))))
