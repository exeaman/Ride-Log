function ConfirmModal({
  isOpen,
  title,
  message,
  onConfirm,
  onCancel
}) {

  if (!isOpen) {
    return null;
  }

  return (
    <div
      className="
      fixed
      inset-0
      bg-black/30
      flex
      items-center
      justify-center
      z-50
      "
    >

      <div
        className="
        bg-white
        rounded-3xl
        p-8
        w-full
        max-w-md
        border
        border-[#E8E1D7]
        shadow-lg
        "
      >

        <h2
          className="
          text-3xl
          mb-4
          "
          style={{
            fontFamily:
              "Playfair Display"
          }}
        >
          {title}
        </h2>

        <p
          className="
          text-[#6B6762]
          mb-8
          "
        >
          {message}
        </p>

        <div
          className="
          flex
          justify-end
          gap-4
          "
        >

          <button
            onClick={onCancel}
            className="
            px-5
            py-3
            rounded-full
            border
            border-[#E8E1D7]
            "
          >
            Cancel
          </button>

          <button
            onClick={onConfirm}
            className="
            px-5
            py-3
            rounded-full
            bg-[#CBB99A]
            text-white
            "
          >
            Confirm
          </button>

        </div>

      </div>

    </div>
  );
}

export default ConfirmModal;